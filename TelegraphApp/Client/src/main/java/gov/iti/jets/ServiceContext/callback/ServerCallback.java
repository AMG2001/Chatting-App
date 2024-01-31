package gov.iti.jets.ServiceContext.callback;

import DTO.MessageDTO;
import DTO.NotificationDTO;
import DTO.RequestDTO;
import DTO.UserDTO;
import RemoteInterfaces.callback.RemoteUserCallback;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerCallback extends UnicastRemoteObject implements RemoteUserCallback
{
    protected ServerCallback() throws RemoteException {
    }

    @Override
    public void recieveMessage(MessageDTO message) {

    }

    @Override
    public void recieveNotification(NotificationDTO notification) throws RemoteException {

    }

    @Override
    public void recieveRequest(RequestDTO request) throws RemoteException {

    }

    @Override
    public void updateContacts(ArrayList<UserDTO> contacts) throws RemoteException {

    }

    @Override
    public void updateContact(UserDTO user) throws RemoteException {

    }
}
