package gov.iti.jets.Service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRegistry {
    private static final int port = 1099;
    private static Registry registry;
    private static ServerRegistry instance;

    private ServerRegistry() {
        try {
            // Create or obtain a reference to the registry
            registry = LocateRegistry.createRegistry(port);
            System.out.println("Remote registry initialized");
        } catch (RemoteException e) {
            System.out.println("Error Initializing remote registry: " + e.getMessage());
        }
    }

    public static ServerRegistry getInstance() {
        if (instance == null) {
            synchronized (ServerRegistry.class) {
                if (instance == null) {
                    instance = new ServerRegistry();
                }
            }
        }
        return instance;
    }

    public Registry getRegistry() {
        return registry;
    }

}


