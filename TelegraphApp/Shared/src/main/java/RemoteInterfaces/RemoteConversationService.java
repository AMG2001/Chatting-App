package RemoteInterfaces;

import DTO.ConversationDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteConversationService extends Remote {
    ConversationDTO getConversationForUserPhone(String userPhone)throws RemoteException;

    //ConversationDTO createGroup(CreateGroupDTO newGroup) throws RemoteException;

}
