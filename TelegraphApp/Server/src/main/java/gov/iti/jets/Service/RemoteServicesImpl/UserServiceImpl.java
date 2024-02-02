package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.UserDTO;
import DTO.UserLoginDTO;
import RemoteInterfaces.RemoteUserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UserServiceImpl extends UnicastRemoteObject implements RemoteUserService {

    public UserServiceImpl() throws RemoteException {
    }

    @Override
    public boolean registerUser(UserDTO newUser) {
        //TODO Check if user exists - Userdao.getbyID(DTO.getphone) and handle null
        //TODO Use  String path = FileManager.SaveProfileImage()
        //TODO Convert DTO to Domain entity
        //TODO User user = UserDao.AddUser(newUser);
        return false;
    }

    @Override
    public UserDTO login(UserLoginDTO loginInfo) {
        //TODO User user =  Dao.getUserByPhone(loginInfo.phone)
        //TODO If not null Check if user.password = loginInfo.password;
        //TODO use FileManager.getProfileImage
        //TODO Convert Domain object to DTO
        //TODO Update Server online array
        //TODO DAO.getAll contacts
        //TODO Use Callback for notifications to online FRIENDS
        //TODO NotificationHandler.sendNotification(ArrayList<String> phonenumbers);

        //If yes DAO get user by phone
        //User Domain object
        //User user = USerDao.getUserbyPhone(loginInfo.phone);
        //Needs to check password
        //User image path to get image file
        //Server----Onlineusers.add();
        // byte[] image = UserProfileFIleSystemManager.getImage(user.getPhone());
        //Create DTO and return
        //Server.notifyOnlineFriends(UserPhone);
        return null;
    }

    @Override
    public ArrayList<UserDTO> getContacts(String userPhone) {
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
