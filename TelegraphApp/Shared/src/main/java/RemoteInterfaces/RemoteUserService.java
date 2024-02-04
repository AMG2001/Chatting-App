package RemoteInterfaces;

import DTO.*;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteUserService extends Remote {
    boolean registerUser(UserDTO newUser)throws RemoteException;
    UserDTO login(UserLoginDTO loginInfo, RemoteCallbackInterface userInterface)throws RemoteException;
    List<ContactDTO> getContacts(String userPhone)throws RemoteException;
    List<GroupsDTO> getGroups(String userPhone) throws RemoteException;
    void logout(LogoutDTO userPhone)throws RemoteException;
    UserDTO updateUser(UpdatedUserDTO newUser)throws RemoteException;
}
