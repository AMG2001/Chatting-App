package RemoteInterfaces;

import DTO.UserDTO;
import DTO.UserLoginDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteUserService extends Remote {
    boolean registerUser(UserDTO newUser)throws RemoteException;
    UserDTO login(UserLoginDTO loginInfo, RemoteCallbackInterface userInterface)throws RemoteException;
    ArrayList<UserDTO> getContacts(String userPhone)throws RemoteException;
    void logout(String userPhone)throws RemoteException;
    UserDTO updateUser(UserDTO newUser)throws RemoteException;
}
