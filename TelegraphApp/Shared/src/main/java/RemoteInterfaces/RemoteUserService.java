package RemoteInterfaces;

import DTO.*;
import DTO.Group.GroupDTO;
import DTO.User.ContactDTO;
import DTO.User.UpdatedUserDTO;
import DTO.User.UserDTO;
import DTO.User.UserLoginDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteUserService extends Remote {
    boolean registerUser(UserDTO newUser)throws RemoteException;
    UserDTO login(UserLoginDTO loginInfo, RemoteCallbackInterface userInterface)throws RemoteException;
    List<ContactDTO> getContacts(String userPhone)throws RemoteException;
    List<GroupDTO> getGroups(String userPhone) throws RemoteException;
    void logout(LogoutDTO userPhone)throws RemoteException;
    UserDTO updateUser(UpdatedUserDTO newUser)throws RemoteException;
    void updateStatus(String userPhone,String usersStatus) throws RemoteException;
}
