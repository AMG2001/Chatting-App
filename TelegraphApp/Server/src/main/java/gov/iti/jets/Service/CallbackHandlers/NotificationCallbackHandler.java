package gov.iti.jets.Service.CallbackHandlers;

import DTO.NotificationDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationCallbackHandler {

    public NotificationCallbackHandler() {
    }

    public void sendNotification(NotificationDTO notification, ConcurrentHashMap<String, RemoteCallbackInterface> clients) {
        try {
            for(Map.Entry<String,RemoteCallbackInterface> entry: clients.entrySet()){
                entry.getValue().recieveNotification(notification);
            }
        } catch (RemoteException e) {
            System.out.println("Error Sending message: " + e.getMessage());
            System.out.println("Notification" + notification.getBody()+" Not sent");
        }
    }
}
