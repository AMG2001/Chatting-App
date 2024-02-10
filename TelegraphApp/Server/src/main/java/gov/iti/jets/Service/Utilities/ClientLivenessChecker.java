package gov.iti.jets.Service.Utilities;

import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientLivenessChecker {

    private ScheduledExecutorService scheduler;
    private volatile boolean livenessCheckingActive;
    public ClientLivenessChecker(){
        this.scheduler = Executors.newScheduledThreadPool(1);
        livenessCheckingActive = false;
    }
    public void startLivenessChecking(){
            scheduler.scheduleAtFixedRate(this::checkLiveness,
                    0, 5, TimeUnit.SECONDS); // Adjust the interval as needed
        livenessCheckingActive = true;

    }
    public void stopLivenessChecking() {
        scheduler.shutdown();
        livenessCheckingActive = false;
    }
    public boolean isLivenessCheckingActive() {
        return livenessCheckingActive;
    }

    private void checkLiveness() {
        HashMap<String, RemoteCallbackInterface> onlineUsers = new HashMap<>(OnlineUserManager.getOnlineUsers());

        if (onlineUsers.isEmpty()) {
            stopLivenessChecking();
            return;
        }
        for (HashMap.Entry<String, RemoteCallbackInterface> entry : onlineUsers.entrySet()) {
            String clientId = entry.getKey();
            RemoteCallbackInterface clientCallback = entry.getValue();

            try {
                // Attempt to invoke the isAlive method on the client's callback interface
                boolean isAlive = clientCallback.isAlive();

                if (!isAlive) {
                    // Client is not alive, remove it from the list of online users
                    OnlineUserManager.removeOnlineUser(clientId);
                    System.out.println("Client " + clientId + " is not alive. Removed from the list.");
                }
            } catch (RemoteException e) {
                System.out.println("Client " + clientId + " is not reachable. Removed from the list.");
                OnlineUserManager.removeOnlineUser(clientId);
            }
        }
    }
}
