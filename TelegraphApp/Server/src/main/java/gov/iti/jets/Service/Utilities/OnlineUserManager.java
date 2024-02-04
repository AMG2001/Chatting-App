package gov.iti.jets.Service.Utilities;

import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    }
    public static void removeOnlineUser(String phone){
        //TODO handle user not exiting in the hashset
        onlineUsers.remove(phone);
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
        //TODO moataz
        return null;
    }
}