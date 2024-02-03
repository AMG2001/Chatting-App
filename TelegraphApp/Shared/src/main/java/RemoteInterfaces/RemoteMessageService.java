package RemoteInterfaces;

import DTO.MessageDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteMessageService extends Remote {
    void sendMessage(MessageDTO message) throws RemoteException;
    List<MessageDTO> getAllMessagesForConversation(String conversationId) throws RemoteException;

}
