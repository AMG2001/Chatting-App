package gov.iti.jets.Service;

import DTO.AttachmentDTO;
import RemoteInterfaces.RemoteAttachmentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AttachmentServiceImpl extends UnicastRemoteObject implements RemoteAttachmentService {
    protected AttachmentServiceImpl() throws RemoteException {
    }
    @Override
    public AttachmentDTO getAttachment(String messageID) throws RemoteException {
        return null;
    }
}
