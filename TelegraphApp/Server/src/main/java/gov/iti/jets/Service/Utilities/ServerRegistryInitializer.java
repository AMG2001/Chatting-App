package gov.iti.jets.Service.Utilities;

import gov.iti.jets.AdminPanel.ProcessLog;
import gov.iti.jets.Service.RemoteServicesImpl.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRegistryInitializer {

    private static final String ATTACHMENT_SERVICE = "AttachmentService";
    private static final String GROUP_SERVICE = "GroupService";
    private static final String MESSAGE_SERVICE = "MessageService";
    private static final String NOTIFICATION_SERVICE = "NotificationService";
    private static final String REQUEST_SERVICE = "RequestService";
    private static final String USER_SERVICE = "UserService";
    private static final int port = 1099;
    private static Registry registry;
    private static String hostname;
    private static ServerRegistryInitializer instance;

    public ServerRegistryInitializer() {
        try {


            // Create or obtain a reference to the registry
            System.out.println("Initializing Registry...");
            registry = LocateRegistry.createRegistry(port);
            System.out.println("Remote registry initialized.");
            System.out.println("Binding Remote Interfaces...");
            bindInterfaces(registry);
            System.out.println("Remote Interfaces Successfully binded to Registry.");
            System.out.println("Registry Initialized.");

        } catch (RemoteException e) {
            System.out.println("Error Initializing remote registry: " + e.getMessage());
        }
    }

    public static ServerRegistryInitializer getInstance() {
        if (instance == null) {
            synchronized (ServerRegistryInitializer.class) {
                if (instance == null) {
                    instance = new ServerRegistryInitializer();
                }
            }
        }
        return instance;
    }
    private void bindInterfaces(Registry registry){
        try{
            AttachmentServiceImpl attachmentService = new AttachmentServiceImpl();
            GroupServiceImpl groupService = new GroupServiceImpl();
            MessageServiceImpl messageService = new MessageServiceImpl();
            NotificationServiceImpl notificationService = new NotificationServiceImpl();
            RequestServiceImpl requestService = new RequestServiceImpl();
            UserServiceImpl userService = new UserServiceImpl();

            registry.rebind(ATTACHMENT_SERVICE,attachmentService);
            registry.rebind(GROUP_SERVICE,groupService);
            registry.rebind(MESSAGE_SERVICE,messageService);
            registry.rebind(NOTIFICATION_SERVICE,notificationService);
            registry.rebind(REQUEST_SERVICE,requestService);
            registry.rebind(USER_SERVICE,userService);

        } catch (RemoteException e) {

            System.out.println("Error Binding Remote services to Registry:"+e.getMessage());
        }

    }
    public Registry getRegistry() {
        return registry;
    }

    private void bindToRegistry(String name, Remote remoteObject){
        try {
            registry.rebind(name,remoteObject);

        } catch (RemoteException e) {
            System.out.println("Error Binding "+name+" to the registry."+e.getMessage());
        }
    }

}


