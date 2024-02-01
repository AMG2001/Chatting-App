package gov.iti.jets.Service;

import DTO.UserDTO;
import DTO.UserLoginDTO;
import RemoteInterfaces.RemoteUserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UserServiceImpl extends UnicastRemoteObject implements RemoteUserService {

    protected UserServiceImpl() throws RemoteException {
    }

    @Override
    public UserDTO registerUser(UserDTO newUser) {
        //Filemanager to save image on HDD and get the filepath
        /*
            //ImageDeSerializer.(newUser.getImage());

           String imagePath = UserProfileFIleSystemManager.SaveImage(newUser.getImage, newUser.getPhone);
        */
        //TODO YOUSEF MAPSTRUCT
        //Convert DTO to Domain object

        //Verify if user already exists
        //DAO - CHECK IF USER EXISTS -- Get by ID and handle null value

        //Use DAO to register the user
        //DAO CREATE USER
        //If successfull Return the same DTO
        //If not successfull return Null
        return null;
    }

    @Override
    public UserDTO login(UserLoginDTO loginInfo) {
        //Check if in database
        //If yes DAO get user by phone
        //User Domain object
        //User user = USerDao.getUserbyPhone(loginInfo.phone);
        //Needs to check password
        //User image path to get image file
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
