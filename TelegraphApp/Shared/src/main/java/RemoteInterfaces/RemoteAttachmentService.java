package RemoteInterfaces;

import DTO.AttachmentDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteAttachmentService extends Remote {
    AttachmentDTO getAttachment(String messageID) throws RemoteException;
}
