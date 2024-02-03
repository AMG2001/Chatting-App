package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.ContactDTO;
import DTO.NotificationDTO;
import DTO.UserDTO;
import DTO.UserLoginDTO;
import RemoteInterfaces.RemoteUserService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.ContactCallbackHandler;
import gov.iti.jets.Service.FileType;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.OnlineUserManager;
import gov.iti.jets.Service.FileSystemUtil;
import gov.iti.jets.Service.Mapstructs.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;

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

                List<RemoteCallbackInterface> remoteFriends
                        = OnlineUserManager.getFriendsFromOnlineList(phoneNumbers);


                NotificationCallbackHandler handler = new NotificationCallbackHandler();
                ContactCallbackHandler contactHandler = new ContactCallbackHandler();

                NotificationDTO notification = new
                        NotificationDTO("1", NotificationType.FRIEND.toString(),
                        LocalDateTime.now(), user.getName() + " is now Online");

                handler.sendNotification(notification, remoteFriends);
                //contactHandler.updateStatus(String phone , UserStatus.ONLINE.toString());

                UserDTO returnedUser = UserMapper.INSTANCE.userToUserDTO(user);
                //TODO Handle Null Image return
                byte[] image = FileSystemUtil.getBytesFromFile(user.getPicture());

                returnedUser.setSerializedImage(image);

                return returnedUser;
            }
        }

    }

    @Override
    public List<ContactDTO> getContacts(String userPhone) {
        //TODO Yousef Return an array of all contacts to the phone number & return it
        /*
        1)ArrayList<User> contacts = UserDAO.get all Contacts by phone
        2) Create array of ContactDTO
        3) Map List<User> to List<ContactDTO> (Name , Phone , Status) -- Mapstruct
        4) get all profile pic for Dto's
        5) Loop over DTO's
        for(int i = 0; i< List.size; i++)
        {
            contactDTO[i].setProfilepic(FileSystemUtil.getBytesfromSystem(User[i].getPicture));
        }
        6) Return List<contactDTO>

         */
        return null;
    }

    @Override
    public void logout(String userPhone) {
        //TODO Moataz
        /*
        1)Handle server- Remove From OnlineUserManager
        2)Get Online Friends - get from DB then Get from hashmap
        3)Update Contact in friends  ContactCallback - UpdateUserStatus for friends
        ContactCallbackHandler.updateStatus(String status, String phone, List<String> phone);
        4)Send Notification using Callback - CREATE NOTIFICATION DTO
        NotificationCallbackHandler.sendNotification(NotificationDTO, List<String> phone)
        5) Set Status in DB UserDAO.setStatus(userPhone);

        * */
        UserDao userDao = new UserDoaImpl();

        User user = userDao.getById(userPhone);

        OnlineUserManager.removeOnlineUser(userPhone);

        List<User> OnlineContacts = userDao.getContactsByPhoneAndStatus(userPhone, UserStatus.ONLINE);
        List<String> OnlineContactsPhones = OnlineContacts.stream()
                .map(User::getPhoneNumber)
                .toList();

        List<RemoteCallbackInterface> OnlineContactsCallBacks
                = OnlineUserManager.gegtFriendsFromOnlineList(OnlineContactsPhones);

        NotificationCallbackHandler handler = new NotificationCallbackHandler();
        ContactCallbackHandler contactHandler = new ContactCallbackHandler();

        NotificationDTO notification = new
                NotificationDTO("1", NotificationType.FRIEND.toString(),
                LocalDateTime.now(), user.getName() + " is now Offline");

        handler.sendNotification(notification, OnlineContactsCallBacks);

        //contactHandler.updateUserStatusForAllContacts(UserStatus.OFFLINE.name(), userPhone, OnlineContactsCallBacks)

        userDao.updateStatus(user.getPhoneNumber(), UserStatus.OFFLINE);
    }

    @Override
    public UserDTO updateUser(UserDTO newUser) {
        //TODO Moataz
        /*
        //Handle failure with null
        1) Save to DB
            Create Domain object and map it from DTO
            Use filemanager for profile
            Set image path in User Domain object

        If successful - callback to all users

        Callback to all friends only if it changes (name or profile pic)

        * */

        return null;
    }
}
