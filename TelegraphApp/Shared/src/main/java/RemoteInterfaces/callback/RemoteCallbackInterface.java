package RemoteInterfaces.callback;

import DTO.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCallbackInterface extends Remote {
    public void recieveMessage(MessageDTO message) throws RemoteException;
    public void recieveNotification(NotificationDTO notification) throws RemoteException;
    public void recieveRequest(RequestDTO request) throws RemoteException;
    //void updateContacts(ArrayList<UserDTO> contacts) throws RemoteException;
    void addContact(ContactDTO newContact) throws RemoteException;
    void updateContactName(String phone,String name) throws RemoteException;
    void updateContactPic(String phone , byte[] picture) throws RemoteException;
    void updateContactStatus(String phone, String status) throws RemoteException;
}
