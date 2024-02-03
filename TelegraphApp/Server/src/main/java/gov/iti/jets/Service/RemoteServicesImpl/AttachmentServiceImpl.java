package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.AttachmentDTO;
import DTO.MessageDTO;
import RemoteInterfaces.RemoteAttachmentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AttachmentServiceImpl extends UnicastRemoteObject implements RemoteAttachmentService {
    public AttachmentServiceImpl() throws RemoteException {
    }

    @Override
    public void sendAttachment(AttachmentDTO attachment) throws RemoteException {

    }

    @Override
    public AttachmentDTO getAllAttachmentForConversation(String conversationId) throws RemoteException {
        return null;
    }
}
