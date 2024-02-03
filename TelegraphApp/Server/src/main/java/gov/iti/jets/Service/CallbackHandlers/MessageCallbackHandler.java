package gov.iti.jets.Service.CallbackHandlers;

import DTO.MessageDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MessageCallbackHandler {
    public MessageCallbackHandler() {
    }

    public void sendMessages(MessageDTO message, ConcurrentHashMap<String, RemoteCallbackInterface> clients) {
        try {
            for(Map.Entry<String,RemoteCallbackInterface> entry: clients.entrySet()){
                entry.getValue().recieveMessage(message);
            }
        } catch (RemoteException e) {
            System.out.println("Error Sending message: " + e.getMessage());
            System.out.println("Message " + message.getMessageId()+" Failed to send.");
        }
    }
}
