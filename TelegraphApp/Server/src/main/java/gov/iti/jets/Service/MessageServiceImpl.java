package gov.iti.jets.Service;

import DTO.MessageDTO;
import RemoteInterfaces.RemoteMessageService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageServiceImpl extends UnicastRemoteObject implements RemoteMessageService {
    protected MessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendMessage(MessageDTO message) throws RemoteException {

    }
}
