package gov.iti.jets.Service;

import DTO.NotificationDTO;
import RemoteInterfaces.RemoteNotificationService;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class NotificationServiceImpl extends UnicastRemoteObject implements RemoteNotificationService {
    protected NotificationServiceImpl() throws RemoteException {
    }
    @Override
    public ArrayList<NotificationDTO> getAllNotifications(String userPhone) throws RemoteException {
        return null;
    }
}