package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.AttachmentDTO;
import RemoteInterfaces.RemoteAttachmentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AttachmentServiceImpl extends UnicastRemoteObject implements RemoteAttachmentService {
    public AttachmentServiceImpl() throws RemoteException {
    }

    @Override
    public void sendAttachment(AttachmentDTO attachment) throws RemoteException {
        //Similar to Send message

    }

    @Override
    public List<AttachmentDTO> getAllAttachmentsForConversation(int conversationId) throws RemoteException {
        //TODO Moataz
        //This will not fetch the attachment data from the filesystem.
        return null;
    }

    @Override
    public byte[] getAttachmentData(String attachmentId) throws RemoteException {
        //TODO moataz
        // THis returns the bytes from the file system or the attachment.
        // The file path is retrieved from the DB
        return new byte[0];
    }
}
