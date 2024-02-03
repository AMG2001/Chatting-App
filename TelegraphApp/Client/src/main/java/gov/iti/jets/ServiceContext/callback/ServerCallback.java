package gov.iti.jets.ServiceContext.callback;

import DTO.*;
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
        //TODO Add the message to the CONVERSATION observable object

    }

    @Override
    public void recieveNotification(NotificationDTO notification) throws RemoteException {

    }

    @Override
    public void recieveRequest(RequestDTO request) throws RemoteException {

    }

    @Override
    public void updateRequest(RequestDTO request) throws RemoteException {

    }

    @Override
    public void addContact(ContactDTO newContact) throws RemoteException {

    }


    @Override
    public void updateContactName(String phone ,String name) throws RemoteException {

    }

    @Override
    public void updateContactPic(String phone , byte[] picture) throws RemoteException {

    }

    @Override
    public void updateContactStatus(String phone, String status) throws RemoteException {

    }
}
