package gov.iti.jets.Service;

import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.util.HashMap;

public class OnlineUsers {
    HashMap<String, RemoteCallbackInterface> onlineUsers;

    OnlineUsers(){
        onlineUsers = new HashMap<>();
    }

    public HashMap<String, RemoteCallbackInterface> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(HashMap<String, RemoteCallbackInterface> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public void addOnlineUser(String phone, RemoteCallbackInterface user){

    }
}
