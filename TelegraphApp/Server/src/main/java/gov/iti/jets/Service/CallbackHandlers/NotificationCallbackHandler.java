package gov.iti.jets.Service.CallbackHandlers;

import DTO.NotificationDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.List;

public class NotificationCallbackHandler {

    public NotificationCallbackHandler() {
    }

    public void sendNotification(NotificationDTO notification, List<RemoteCallbackInterface> clients) {
        try {
            for (RemoteCallbackInterface client : clients) {
                client.recieveNotification(notification);
            }
        } catch (RemoteException e) {
            System.out.println("Error Sending notification: " + e.getMessage());
            System.out.println("Notification " + notification.getBody() + " Not sent");
        }
    }

}
