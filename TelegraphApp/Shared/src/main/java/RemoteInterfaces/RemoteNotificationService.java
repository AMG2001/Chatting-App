package RemoteInterfaces;

import DTO.NotificationDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteNotificationService extends Remote {
    ArrayList<NotificationDTO> getAllNotifications(String userPhone)throws RemoteException;
}
