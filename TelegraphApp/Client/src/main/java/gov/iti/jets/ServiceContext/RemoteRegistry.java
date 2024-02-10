package gov.iti.jets.ServiceContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteRegistry {
    private static final int port = 1099;
    private static String host;
    private static Registry registry;
    private static RemoteRegistry instance;

    private RemoteRegistry() {
        // Example usage:
        ClientListener clientListener = new ClientListener();
        clientListener.startListening();

        // Keep the main thread alive until the desired IP is found
        while (clientListener.isListening()) {
            try {
                Thread.sleep(1000); // Sleep for 1 second (adjust as needed)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Retrieve the received IP address
        host = clientListener.getReceivedIpAddress();
        System.out.println(host+"Ahmed");
        // Create or obtain a reference to the registry
        try {
            registry = LocateRegistry.getRegistry(host, port);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Remote registry initialized");
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
