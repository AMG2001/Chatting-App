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
        ClientListener listener = new ClientListener();
        listener.startListening();
        // Wait for 5 seconds to receive the IP address
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Print the received IP address
        System.out.println("Received IP address: " + listener.receivedIpAddress);
        // Stop listening
        listener.stopListening();
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
