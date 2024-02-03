package RemoteInterfaces;

import DTO.ContactDTO;
import DTO.UserDTO;
import DTO.UserLoginDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteUserService extends Remote {
    boolean registerUser(UserDTO newUser)throws RemoteException;
    UserDTO login(UserLoginDTO loginInfo, RemoteCallbackInterface userInterface)throws RemoteException;
    List<ContactDTO> getContacts(String userPhone)throws RemoteException;
    void logout(String userPhone)throws RemoteException;
    UserDTO updateUser(UserDTO newUser)throws RemoteException;
}
