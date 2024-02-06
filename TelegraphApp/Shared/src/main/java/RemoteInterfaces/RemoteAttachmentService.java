package RemoteInterfaces;

import DTO.AttachmentDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
public interface RemoteAttachmentService extends Remote {
    void sendAttachment(AttachmentDTO attachment) throws RemoteException;
    public List<AttachmentDTO> getAllAttachmentsForConversation(int conversationId) throws RemoteException;
    byte[] getAttachmentData(int conversationId, int attachmentId) throws RemoteException;
}
