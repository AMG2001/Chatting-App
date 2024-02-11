package gov.iti.jets.Service.Utilities;

import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.AdminPanel.ProcessLog;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.ContactCallbackHandler;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientLivenessChecker {

    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private volatile boolean livenessCheckingActive = false;

    public void startLivenessChecking() {
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

        if (!onlineUsers.isEmpty()) {
            for (HashMap.Entry<String, RemoteCallbackInterface> entry : onlineUsers.entrySet()) {
                String clientId = entry.getKey();
                RemoteCallbackInterface clientCallback = entry.getValue();
                try {
                    // Attempt to invoke the isAlive method on the client's callback interface
                    boolean isAlive = clientCallback.isAlive();

                    if (!isAlive) {
                        // Client is not alive, remove it from the list of online users
                        OnlineUserManager.removeOnlineUser(clientId);
                        ContactCallbackHandler handler = new ContactCallbackHandler();
                        List<String> OnlineContactsPhones;
                        List<RemoteCallbackInterface> OnlineContactsCallBacks=new ArrayList<>();
                        UserDao userDao = new UserDoaImpl();
                        List<User> OnlineContacts;
                        OnlineContacts = userDao.getContactsByPhoneAndStatus(clientId, UserStatus.ONLINE);
                        OnlineContactsPhones = OnlineContacts.stream()
                                .map(User::getPhoneNumber)
                                .toList();

                        OnlineContactsCallBacks = OnlineUserManager.getFriendsFromOnlineList(OnlineContactsPhones);
                        handler.updateContactStatus(clientId,UserStatus.OFFLINE.toString(),OnlineContactsCallBacks);
                        ProcessLog.appendToProcessLog("User  "+ clientId + " from Callback Interface");
                        userDao.updateStatus(clientId,UserStatus.OFFLINE);
                        System.out.println("Client " + clientId + " is not alive. Removed from the list.");
                    }
                } catch (RemoteException e) {
                    ContactCallbackHandler handler = new ContactCallbackHandler();
                    List<String> OnlineContactsPhones;
                    List<RemoteCallbackInterface> OnlineContactsCallBacks=new ArrayList<>();
                    UserDao userDao = new UserDoaImpl();
                    List<User> OnlineContacts;
                    OnlineContacts = userDao.getContactsByPhoneAndStatus(clientId, UserStatus.ONLINE);
                    OnlineContactsPhones = OnlineContacts.stream()
                            .map(User::getPhoneNumber)
                            .toList();

                    OnlineContactsCallBacks = OnlineUserManager.getFriendsFromOnlineList(OnlineContactsPhones);
                    handler.updateContactStatus(clientId,UserStatus.OFFLINE.toString(),OnlineContactsCallBacks);
                    ProcessLog.appendToProcessLog("User  "+ clientId + " from Callback Interface");
                    System.out.println("Client " + clientId + " is not reachable. Removed from the list.");
                    userDao.updateStatus(clientId,UserStatus.OFFLINE);
                    OnlineUserManager.removeOnlineUser(clientId);
                }
            }
        }
    }
}
