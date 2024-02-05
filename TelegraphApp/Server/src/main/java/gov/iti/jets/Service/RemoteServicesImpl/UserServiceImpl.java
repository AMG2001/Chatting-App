package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.*;
import RemoteInterfaces.RemoteUserService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.ContactCallbackHandler;
import gov.iti.jets.Service.Mapstructs.ContactMapper;
import gov.iti.jets.Service.Mapstructs.ConversationMapper;
import gov.iti.jets.Service.Utilities.FileType;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.Utilities.OnlineUserManager;
import gov.iti.jets.Service.Utilities.FileSystemUtil;
import gov.iti.jets.Service.Mapstructs.UserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserServiceImpl extends UnicastRemoteObject implements RemoteUserService {

    public UserServiceImpl() throws RemoteException {
    }

    @Override
    public boolean registerUser(UserDTO newUser) {
        UserDao userDao = new UserDoaImpl();
        //Check if already exists in DB
        if (userDao.getById(newUser.getPhoneNumber()) == null) {
            //Map DTO to Domain & add to DB
            User user = UserMapper.INSTANCE.userDTOToUser(newUser);
            //Store Image in file system and store the path
            String profileImagePath = FileSystemUtil.storeByteArrayAsFile
                    (newUser.getSerializedImageURL(), newUser.getPhoneNumber(), FileType.PROFILE_PIC);
            user.setPicture(profileImagePath);
            userDao.add(user);
            return true;
        } else {
            System.out.println("User already exists");
            return false;
        }
    }

    @Override
    public UserDTO login(UserLoginDTO loginInfo, RemoteCallbackInterface remoteUserInterface) {

        UserDao userDao = new UserDoaImpl();
        User user = userDao.getById(loginInfo.getPhoneNumber());
        //User phone not found
        if (user == null) {
            //TODO Should this return an exception ?
            System.out.println("User does not exist");
            return null;
        } else {
            //Check if password is correct
            if (!loginInfo.getPassword().equals(user.getPassword())) {
                System.out.println("Password incorrect");
                return null;
            } else {
                //Update user status to online in DB and in returned Object
                userDao.updateStatus(user.getPhoneNumber(), UserStatus.ONLINE);
                user.setStatus(UserStatus.ONLINE);

                //Add online user to the Hashmap
                OnlineUserManager.addOnlineUser(user.getPhoneNumber(), remoteUserInterface);

                //Notify Friends that user is online
                List<User> friends = userDao.getContactsByPhoneAndStatus(user.getPhoneNumber(), UserStatus.ONLINE);
                List<String> phoneNumbers = friends.stream()
                        .map(User::getPhoneNumber)
                        .toList();

                //Fetch CallbackInterfaces for online Contacts
                List<RemoteCallbackInterface> remoteContacts
                        = OnlineUserManager.getFriendsFromOnlineList(phoneNumbers);


                //Initialize callback Handlers
                NotificationCallbackHandler handler = new NotificationCallbackHandler();
                ContactCallbackHandler contactHandler = new ContactCallbackHandler();

                //Create Notification object
                NotificationDTO notification = new
                        NotificationDTO("1", NotificationType.FRIEND.toString(),
                        LocalDateTime.now(), user.getName() + " is now Online");

                //Send notification to contacts (User is online)
                handler.sendNotification(notification, remoteContacts);
                contactHandler.updateContactStatus(
                        user.getPhoneNumber(),UserStatus.ONLINE.toString(),remoteContacts);

                //Map user domain object to DTO
                UserDTO returnedUser = UserMapper.INSTANCE.userToUserDTO(user);
                //TODO Handle Null Image return
                //Fetch user Profile pic from FileSystem
                byte[] image = FileSystemUtil.getBytesFromFile(user.getPicture());
                returnedUser.setSerializedImage(image);


                return returnedUser;
            }
        }

    }

    @Override
    public List<ContactDTO> getContacts(String userPhone) {

        // get all contacts from DB
        UserDao userDao = new UserDoaImpl();
        List<User> contactsDB = userDao.getAllContactsByPhone(userPhone);

        ConversationDao conversationDao = new ConversationDaoImpl();

        List<ContactDTO> contactDTOS = new ArrayList<>();

        for(User contactDB :contactsDB){
            ContactDTO contactDTO=ContactMapper.INSTANCE.userToContactDTO(contactDB);

            //get individual conversation between User and his contact from DB
            int conversationId = conversationDao.getIndividualConversationId(userPhone,contactDTO.getPhoneNumber());
            Conversation conversationDomain = conversationDao.getById(conversationId);

            // map conversation domain to conversation dto and set messages and attachments to empty lists
            ConversationDTO conversationDTO = ConversationMapper.INSTANCE.conversationToConversationDTO(conversationDomain);
            conversationDTO.setMessages(new ArrayList<>());
            conversationDTO.setAttachments(new ArrayList<>());

            // get contact image
            byte[] contactImage = FileSystemUtil.getBytesFromFile(contactDB.getPicture());

            // set conversation and picture to contact dto
            contactDTO.setConversation(conversationDTO);
            contactDTO.setProfilepic(contactImage);

            // add contact dto to list of contacts
            contactDTOS.add(contactDTO);
        }

        return contactDTOS;
    }

    @Override
    public List<GroupDTO> getGroups(String userPhone) throws RemoteException {

        // get group conversation from DB
        ConversationDao conversationDao = new ConversationDaoImpl();
        List<Conversation> groupConversationsDB= conversationDao.getGroupConversationsByPhone(userPhone);

        List<GroupDTO> groupDTOS = new ArrayList<>();
        for (Conversation groupConversationDB: groupConversationsDB){

            GroupDTO groupDTO = ConversationMapper.INSTANCE.conversationToGroupDTO(groupConversationDB);

            // // map groupConversationDB to conversation dto and set messages and attachments to empty lists
            ConversationDTO conversationDTO = ConversationMapper.INSTANCE.conversationToConversationDTO(groupConversationDB);
            conversationDTO.setMessages(new ArrayList<>());
            conversationDTO.setAttachments(new ArrayList<>());

            // add conversation dto to group dto
            groupDTO.setConversation(conversationDTO);

            // get group image
            byte[] groupImage = FileSystemUtil.getBytesFromFile(groupConversationDB.getConversationImage());

            // add group image to group dto
            groupDTO.setGroupImage(groupImage);

            // get group members from DB
            List<User> groupMembersDB = conversationDao.getGroupMembersByConversationId(groupConversationDB.getConversationId());

            List<GroupMemberDTO> groupMemberDTOS = new ArrayList<>();
            for (User groupMemberDB : groupMembersDB){

                GroupMemberDTO groupMemberDTO=UserMapper.INSTANCE.userToGroupMemberDTO(groupMemberDB);

                byte[] groupMemberImage = FileSystemUtil.getBytesFromFile(groupMemberDB.getPicture());
                groupMemberDTO.setProfilepic(groupMemberImage);

                groupMemberDTOS.add(groupMemberDTO);
            }

            // add group members to group dto
            groupDTO.setGroupMembers(groupMemberDTOS);

            // add group dto to list of group dtos
            groupDTOS.add(groupDTO);
        }


        return groupDTOS;
    }

    @Override
    public void logout(LogoutDTO loggedOutUser) {

        UserDao userDao = new UserDoaImpl();

        OnlineUserManager.removeOnlineUser(loggedOutUser.getUserPhone());

        List<User> OnlineContacts = userDao.getContactsByPhoneAndStatus(loggedOutUser.getUserPhone(), UserStatus.ONLINE);
        List<String> OnlineContactsPhones = OnlineContacts.stream()
                .map(User::getPhoneNumber)
                .toList();

        List<RemoteCallbackInterface> OnlineContactsCallBacks
                = OnlineUserManager.getFriendsFromOnlineList(OnlineContactsPhones);

        NotificationCallbackHandler handler = new NotificationCallbackHandler();
        ContactCallbackHandler contactHandler = new ContactCallbackHandler();

        NotificationDTO notification = new
                NotificationDTO("1", NotificationType.FRIEND.toString(),
                LocalDateTime.now(), loggedOutUser.getName() + " is now Offline");

        handler.sendNotification(notification, OnlineContactsCallBacks);

        contactHandler.updateContactStatus(loggedOutUser.getUserPhone(),UserStatus.OFFLINE.name(),OnlineContactsCallBacks);

        userDao.updateStatus(loggedOutUser.getUserPhone(), UserStatus.OFFLINE);
        //TODO handle exception from DB
    }

    @Override
    public UserDTO updateUser(UpdatedUserDTO updatedUserDTO) {

        UserDao userDao = new UserDoaImpl();

        User oldUserModel = userDao.getById(updatedUserDTO.getPhoneNumber());

        User newUserModel = UserMapper.INSTANCE.updatedUserDTOToUser(updatedUserDTO);
        userDao.update(newUserModel);

        List<User> OnlineContacts;
        List<String> OnlineContactsPhones;
        List<RemoteCallbackInterface> OnlineContactsCallBacks=new ArrayList<>();
        ContactCallbackHandler contactHandler=new ContactCallbackHandler();

        if(updatedUserDTO.getPicChanged() || oldUserModel.getName()!=updatedUserDTO.getName()){

             OnlineContacts = userDao.getContactsByPhoneAndStatus(updatedUserDTO.getPhoneNumber(), UserStatus.ONLINE);
             OnlineContactsPhones = OnlineContacts.stream()
                    .map(User::getPhoneNumber)
                    .toList();

             OnlineContactsCallBacks = OnlineUserManager.getFriendsFromOnlineList(OnlineContactsPhones);

             contactHandler = new ContactCallbackHandler();
        }

        if (updatedUserDTO.getPicChanged()){
            String profileImagePath = FileSystemUtil.storeByteArrayAsFile
                    (updatedUserDTO.getSerializedImage(), updatedUserDTO.getName(), FileType.PROFILE_PIC);
            userDao.updateProfilePic(updatedUserDTO.getPhoneNumber(),profileImagePath);

            contactHandler.updateContactPic(updatedUserDTO.getPhoneNumber(),updatedUserDTO.getSerializedImage(),OnlineContactsCallBacks);
        }

        if(oldUserModel.getName()!=updatedUserDTO.getName()) {
            contactHandler.updateContactName(updatedUserDTO.getPhoneNumber(), updatedUserDTO.getName(), OnlineContactsCallBacks);
        }

        User updatedUserModel = userDao.getById(updatedUserDTO.getPhoneNumber());

        UserDTO returnedUserDTO = UserMapper.INSTANCE.userToUserDTO(updatedUserModel);

        //TODO Handle Null Image return
        byte[] image = FileSystemUtil.getBytesFromFile(updatedUserModel.getPicture());
        returnedUserDTO.setSerializedImage(image);

        return returnedUserDTO;
    }
}
