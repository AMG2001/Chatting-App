package gov.iti.jets.Service.Utilities;

import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.AdminPanel.ProcessLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//TODO create effective logging
public class OnlineUserManager {
    private static ConcurrentHashMap<String, RemoteCallbackInterface> onlineUsers = new ConcurrentHashMap<>();

    OnlineUserManager(){
        onlineUsers = new ConcurrentHashMap<>();
    }

    public static ConcurrentHashMap<String, RemoteCallbackInterface> getOnlineUsers() {
        return onlineUsers;
    }

    public static void setOnlineUsers(ConcurrentHashMap<String, RemoteCallbackInterface> onlineUsers) {
        OnlineUserManager.onlineUsers = onlineUsers;
    }

    public static void addOnlineUser(String phone, RemoteCallbackInterface user){
        //TODO Handle user already existing
        onlineUsers.put(phone,user);
        ProcessLog.appendToProcessLog("User Added to Callback Interface");

    }
    public static void removeOnlineUser(String phone){
        //TODO handle user not exiting in the hashset
        onlineUsers.remove(phone);
        ProcessLog.appendToProcessLog("User removed from Callback Interface");
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
