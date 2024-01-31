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
        //Convert DTO to Domain object
        //Verify if user already exists
        //Use DAO to register the user
        return null;
    }

    @Override
    public UserDTO login(UserLoginDTO loginInfo) {
        return null;
    }

    @Override
    public ArrayList<UserDTO> getContacts(String userPhone) {
        return null;
    }

    @Override
    public void logout(String userPhone) {

    }

    @Override
    public UserDTO updateUser(UserDTO newUser) {
        return null;
    }
}
