package RemoteInterfaces.callback;

import DTO.MessageDTO;
import DTO.NotificationDTO;
import DTO.RequestDTO;
import DTO.UserDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteUserCallback extends Remote {
    public void recieveMessage(MessageDTO message) throws RemoteException;
    public void recieveNotification(NotificationDTO notification) throws RemoteException;
    public void recieveRequest(RequestDTO request) throws RemoteException;
    void updateContacts(ArrayList<UserDTO> contacts) throws RemoteException;
    //Contact updated their status
    void updateContact(UserDTO user) throws RemoteException;
}
