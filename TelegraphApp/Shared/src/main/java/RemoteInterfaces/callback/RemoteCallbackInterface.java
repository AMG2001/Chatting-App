package RemoteInterfaces.callback;

import DTO.*;
import DTO.Group.GroupDTO;
import DTO.Request.RequestRecieveDTO;
import DTO.Request.RequestResponseDTO;
import DTO.Request.RequestSendDTO;
import DTO.User.ContactDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCallbackInterface extends Remote {

    boolean isAlive() throws RemoteException;
    void serverShutdown() throws RemoteException;
    void serverStart() throws RemoteException;
    void recieveMessage(MessageDTO message) throws RemoteException;
    void recieveAttachment(AttachmentDTO attachment) throws RemoteException;

    void recieveNotification(NotificationDTO notification) throws RemoteException;

    void recieveRequest(RequestRecieveDTO request) throws RemoteException;

    void updateRequest(RequestResponseDTO request) throws RemoteException;

    void addContact(ContactDTO newContact) throws RemoteException;
    void addGroup(GroupDTO newGroup) throws RemoteException;

    void updateContactName(String phone, String name) throws RemoteException;

    void updateContactPic(String phone, byte[] picture) throws RemoteException;

    void updateContactStatus(String phone, String status) throws RemoteException;

}
