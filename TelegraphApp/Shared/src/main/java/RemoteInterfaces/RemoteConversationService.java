package RemoteInterfaces;

import DTO.ConversationDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteConversationService extends Remote {
    ConversationDTO getAllConversationForUser(String userPhone)throws RemoteException;

}
