package gov.iti.jets.ServiceContext;

import RemoteInterfaces.RemoteNotificationService;
import RemoteInterfaces.RemoteUserService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class NotificationService {
    private static NotificationService instance;
    private RemoteNotificationService service;

    private NotificationService() {
        try {
            Registry registry = RemoteRegistry.getInstance().getRegistry();
            service = (RemoteNotificationService) registry.lookup("NotifcationService");

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error getting Notification Service from remote registry: "+e.getMessage());
        }
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            synchronized (NotificationService.class) {
                if (instance == null) {
                    instance = new NotificationService();
                }
            }
        }
        return instance;
    }

    public RemoteNotificationService getRemoteService()
    {
        return service;
    }


}
