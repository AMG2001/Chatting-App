package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.AttachmentDTO;
import RemoteInterfaces.RemoteAttachmentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AttachmentServiceImpl extends UnicastRemoteObject implements RemoteAttachmentService {
    public AttachmentServiceImpl() throws RemoteException {
    }
    @Override
    public AttachmentDTO getAttachment(String messageID) throws RemoteException {


        return null;
    }

    @Override
    public void sendAttachment(AttachmentDTO attachment) throws RemoteException {

    }
}
