package gov.iti.jets.Service.CallbackHandlers;

import DTO.MessageDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.List;


public class MessageCallbackHandler {
    public MessageCallbackHandler() {
    }

    public void sendMessages(MessageDTO message, List<RemoteCallbackInterface> clients) {
        try {
            for (RemoteCallbackInterface client : clients) {
                client.recieveMessage(message);
            }
        } catch (RemoteException e) {
            System.out.println("Error Sending message: " + e.getMessage());
            System.out.println("Message " + message.getMessageId() + " Failed to send.");
        }
    }
}
