package gov.iti.jets.ServiceContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteRegistry {
    private static final int port= 1099;
    private static String host;
    private static Registry registry;
    private static RemoteRegistry instance;
    private RemoteRegistry() {
        try {
            host = InetAddress.getLocalHost().getHostAddress();
            // Create or obtain a reference to the registry
            registry = LocateRegistry.getRegistry(host, port);
            System.out.println("Remote registry initialized");
        } catch (RemoteException e) {
            System.out.println("Error Initializing remote registry: "+ e.getMessage());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    public static RemoteRegistry getInstance() {
        if (instance == null) {
            synchronized (RemoteRegistry.class) {
                if (instance == null) {
                    instance = new RemoteRegistry();
                }
            }
        }
        return instance;
    }
    public Registry getRegistry() {
        return registry;
    }

}
