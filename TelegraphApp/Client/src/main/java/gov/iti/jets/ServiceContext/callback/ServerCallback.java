package gov.iti.jets.ServiceContext.callback;

import DTO.MessageDTO;
import DTO.NotificationDTO;
import DTO.RequestDTO;
import DTO.UserDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerCallback extends UnicastRemoteObject implements RemoteCallbackInterface
{
    public ServerCallback() throws RemoteException {
    }

    @Override
    public void recieveMessage(MessageDTO message) {
        //TODO Add the message to the CONVERSATION obserable object

    }

    @Override
    public void recieveNotification(NotificationDTO notification) throws RemoteException {

    }

    @Override
    public void recieveRequest(RequestDTO request) throws RemoteException {

    }

    @Override
    public void addContact(UserDTO newContact) throws RemoteException {

    }


    @Override
    public void updateContactName(String name) throws RemoteException {

    }

    @Override
    public void updateContactPic(byte[] picture) throws RemoteException {

    }

    @Override
    public void updateContactStatus(String status) throws RemoteException {

    }
}
