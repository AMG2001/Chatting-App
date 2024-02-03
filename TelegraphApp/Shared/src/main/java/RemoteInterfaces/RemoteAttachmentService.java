package RemoteInterfaces;

import DTO.AttachmentDTO;
import DTO.MessageDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteAttachmentService extends Remote {
    void sendAttachment(AttachmentDTO attachment) throws RemoteException;
    public AttachmentDTO getAllAttachmentForConversation(String conversationId) throws RemoteException;
}
