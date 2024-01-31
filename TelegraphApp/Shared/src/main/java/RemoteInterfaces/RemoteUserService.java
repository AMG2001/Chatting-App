package RemoteInterfaces;

import DTO.UserDTO;
import DTO.UserLoginDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteUserService extends Remote {
    UserDTO registerUser(UserDTO newUser)throws RemoteException;

    UserDTO login(UserLoginDTO loginInfo)throws RemoteException;
    ArrayList<UserDTO> getContacts(String userPhone)throws RemoteException;
    void logout(String userPhone)throws RemoteException;
    UserDTO updateUser(UserDTO newUser)throws RemoteException;
}
