package gov.iti.jets.Service.RemoteServicesImpl;

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
import gov.iti.jets.Service.FileType;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.OnlineUserManager;
import gov.iti.jets.Service.ProfilepicStorageUtil;
import gov.iti.jets.Service.mappers.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class UserServiceImpl extends UnicastRemoteObject implements RemoteUserService {

    public UserServiceImpl() throws RemoteException {
    }

    @Override
    public boolean registerUser(UserDTO newUser) {
        UserDao userDao = new UserDoaImpl();
        //Check if already exists in DB
        if(userDao.getById(newUser.getPhoneNumber()) == null)
        {
            //Map DTO to Domain & add to DB
            User user = UserMapper.INSTANCE.userDTOToUser(newUser);
            //Store Image in file system and store the path
            String profileImagePath = ProfilepicStorageUtil.storeByteArrayAsFile
                    (newUser.getSerializedImageURL(),newUser.getPhoneNumber(), FileType.PROFILE_PIC);
            user.setPicture(profileImagePath);
            userDao.add(user);
            return true;
        }
        System.out.println("User already exists");
        return false;
    }

    @Override
    public UserDTO login(UserLoginDTO loginInfo, RemoteCallbackInterface remoteUserInterface) {

        UserDao userDao = new UserDoaImpl();
        User user = userDao.getById(loginInfo.getPhoneNumber());
        //User phone not found
        if(user==null)
        {
            //TODO Should this return an exception ?
            System.out.println("User does not exist");
            return null;
        }
        else {
            //Check if password is correct
            if(!loginInfo.getPassword().equals(user.getPassword()))
            {
                System.out.println("Password incorrect");
                return null;
            }
            else
            {
                //Update user status to online in DB and in returned Object
                userDao.updateStatus(user.getPhoneNumber(),UserStatus.ONLINE);
                user.setStatus(UserStatus.ONLINE);

                //Add online user to the Hashmap
                OnlineUserManager.addOnlineUser(user.getPhoneNumber(),remoteUserInterface);

                //Notify Friends that user is online
                List<User> friends = userDao.getContactsByPhoneAndStatus(user.getPhoneNumber(), UserStatus.ONLINE);
                List<String> phoneNumbers = friends.stream()
                        .map(User::getName)
                        .toList();

                ConcurrentHashMap<String,RemoteCallbackInterface> remoteFriends
                        = OnlineUserManager.getFriendsFromOnlineList(phoneNumbers);


                NotificationCallbackHandler handler = new NotificationCallbackHandler();

                NotificationDTO notification = new
                        NotificationDTO("1", NotificationType.FRIEND.toString(),
                        LocalDateTime.now(),user.getName()+" is now Online");

                handler.sendNotification(notification,remoteFriends);

                UserDTO returnedUser = UserMapper.INSTANCE.userToUserDTO(user);
                //TODO Handle Null Image return
                byte[] image= ProfilepicStorageUtil.getBytesFromFile(user.getPicture());

                returnedUser.setSerializedImage(image);

                return returnedUser;
            }
        }

    }

    @Override
    public ArrayList<UserDTO> getContacts(String userPhone) {
        //TODO Yousef Return an array of all contacts to the phone number & return it
        return null;
    }

    @Override
    public void logout(String userPhone) {
        //Send Notification to all friends

    }

    @Override
    public UserDTO updateUser(UserDTO newUser) {
        return null;
    }
}
