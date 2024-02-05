package RemoteInterfaces;

import DTO.ConversationDTO;
import DTO.Group.GroupCreationDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGroupService extends Remote {
    void createGroup(GroupCreationDTO newGroup)throws RemoteException;

}
