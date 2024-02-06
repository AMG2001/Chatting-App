package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.ConversationDTO;
import DTO.Group.GroupCreationDTO;
import DTO.Group.GroupDTO;
import DTO.Group.GroupMemberDTO;
import DTO.NotificationDTO;
import RemoteInterfaces.RemoteGroupService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.dao.UserConversationDao;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.UserConversationDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.GroupCallbackHandler;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.Mappers.UserMapper;
import gov.iti.jets.Service.Utilities.FileSystemUtil;
import gov.iti.jets.Service.Utilities.FileType;
import gov.iti.jets.Service.Utilities.OnlineUserManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl extends UnicastRemoteObject implements RemoteGroupService {
    public GroupServiceImpl() throws RemoteException {
    }

    @Override
    public void createGroup(GroupCreationDTO newGroup) throws RemoteException {

        ConversationDao conversationDao =new ConversationDaoImpl();
        Conversation conversationModel = new Conversation();
        conversationModel.setConversationName(newGroup.getGroupName());

        // create group and add founder to it and return created group id
        int groupId=conversationDao.createGroupConversation(newGroup.getGroupFounder(),conversationModel);

        // store group image to file system and get its path
        String groupImagePath = FileSystemUtil.storeByteArrayAsFile
                (newGroup.getGroupImg(), String.valueOf(groupId), FileType.GROUP_PIC);

        // insert group image into DB
        conversationDao.updateGroupImage(groupId,groupImagePath);

        // insert group members into DB
        UserConversationDao userConversationDao = new UserConversationDoaImpl();
        userConversationDao.addGroupMembers(groupId,newGroup.getGroupMembers());

        // get group and notification callback handlers
        GroupCallbackHandler groupHandler = new GroupCallbackHandler();
        NotificationCallbackHandler notificationHandler=new NotificationCallbackHandler();

        // fetch remote callbacks interfaces for both group founder and members
        List<RemoteCallbackInterface> groupMembersCallbacks
                = OnlineUserManager.getFriendsFromOnlineList(newGroup.getGroupMembers());
        RemoteCallbackInterface groupFounderCallBack = OnlineUserManager.getOnlineUser(newGroup.getGroupFounder());

        // create appropriate notification for group founder and members
        NotificationDTO notificationToGroupFounder = new NotificationDTO("1", NotificationType.FRIEND.toString()
                , LocalDateTime.now(), "You've created the group successfully");
        NotificationDTO notificationToGroupMembers = new NotificationDTO("1", NotificationType.FRIEND.toString()
                , LocalDateTime.now(), newGroup.getGroupFounder()+"added you to '"+newGroup.getGroupName()+"' group");

        //send notifications to group founder and members (CALLBACK)
        notificationHandler.sendNotificationtoClient(notificationToGroupFounder,groupFounderCallBack);
        notificationHandler.sendNotification(notificationToGroupMembers,groupMembersCallbacks);

        // construct group dto
        GroupDTO newGroupDTO=new GroupDTO();
        newGroupDTO.setConversation(new ConversationDTO(groupId,"GROUP",new ArrayList<>(),new ArrayList<>()));
        newGroupDTO.setGroupName(newGroup.getGroupName());
        newGroupDTO.setGroupImage(newGroup.getGroupImg());

        // get group members from DB
        List<User> groupMembersDB = conversationDao.getGroupMembersByConversationId(groupId);

        List<GroupMemberDTO> groupMemberDTOS = new ArrayList<>();
        for (User groupMemberDB : groupMembersDB){

            GroupMemberDTO groupMemberDTO= UserMapper.userToGroupMemberDTO(groupMemberDB);

            byte[] groupMemberImage = FileSystemUtil.getBytesFromFile(groupMemberDB.getPicture());
            groupMemberDTO.setProfilepic(groupMemberImage);

            groupMemberDTOS.add(groupMemberDTO);
        }
        // add group members to group dto
        newGroupDTO.setGroupMembers(groupMemberDTOS);

        // add group founder callback to group members callbacks
        groupMembersCallbacks.add(groupFounderCallBack);

        //call createGroup callback (CALLBACK)
        groupHandler.createGroup(newGroupDTO,groupMembersCallbacks);
    }
}
