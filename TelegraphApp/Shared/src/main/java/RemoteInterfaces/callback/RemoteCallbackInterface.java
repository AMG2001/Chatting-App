package RemoteInterfaces.callback;

import DTO.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCallbackInterface extends Remote {
    void recieveMessage(MessageDTO message) throws RemoteException;
    void recieveAttachment(AttachmentDTO attachment) throws RemoteException;

    void recieveNotification(NotificationDTO notification) throws RemoteException;

    void recieveRequest(RequestDTO request) throws RemoteException;

    void updateRequest(RequestDTO request) throws RemoteException;

    void addContact(ContactDTO newContact) throws RemoteException;
    void addGroup(GroupDTO newGroup) throws RemoteException;

    void updateContactName(String phone, String name) throws RemoteException;

    void updateContactPic(String phone, byte[] picture) throws RemoteException;

    void updateContactStatus(String phone, String status) throws RemoteException;

}
