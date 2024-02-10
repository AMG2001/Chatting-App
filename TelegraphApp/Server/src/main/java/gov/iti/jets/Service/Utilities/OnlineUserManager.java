package gov.iti.jets.Service.Utilities;

import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.AdminPanel.ProcessLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//TODO create effective logging
public class OnlineUserManager {
    private static final ClientLivenessChecker crashHandler = new ClientLivenessChecker();
    private static ConcurrentHashMap<String, RemoteCallbackInterface> onlineUsers = new ConcurrentHashMap<>();

    public static List<RemoteCallbackInterface> getOnlineUsersCallbackInterfaces() {
        return  onlineUsers.values().stream().toList();
    }

    public static ConcurrentHashMap<String, RemoteCallbackInterface> getOnlineUsers() {
        return onlineUsers;
    }

    public static void setOnlineUsers(ConcurrentHashMap<String, RemoteCallbackInterface> onlineUsers) {
        OnlineUserManager.onlineUsers = onlineUsers;
    }

    public static void addOnlineUser(String phone, RemoteCallbackInterface user){
        //TODO Handle user already existing
//        if(onlineUsers.isEmpty()) {
//            if(!crashHandler.isLivenessCheckingActive())
//                crashHandler.startLivenessChecking();
//        }
        onlineUsers.put(phone,user);
        ProcessLog.appendToProcessLog("User "+ phone +" Added to Callback Interface");

    }
    public static void removeOnlineUser(String phone){
        //TODO handle user not exiting in the hashset
//        if(onlineUsers.size() == 1)
//            crashHandler.stopLivenessChecking();
//        onlineUsers.remove(phone);
        ProcessLog.appendToProcessLog("User  "+ phone + " from Callback Interface");
    }

    public static List<RemoteCallbackInterface> getFriendsFromOnlineList(List<String> phones) {
        List<RemoteCallbackInterface> result = new ArrayList<>();

        for (String key : phones) {
            if (onlineUsers.containsKey(key)) {
                RemoteCallbackInterface value = onlineUsers.get(key);
                result.add(value);
            }
        }

        return result;
    }
    public static RemoteCallbackInterface getOnlineUser(String phone)
    {
        RemoteCallbackInterface remoteCallbackInterface=null;

        //TODO handle if remoteCallbackInterface doesn't exist
        if (onlineUsers.containsKey(phone)) {
            remoteCallbackInterface = onlineUsers.get(phone);
        }

        return remoteCallbackInterface;
    }
}
