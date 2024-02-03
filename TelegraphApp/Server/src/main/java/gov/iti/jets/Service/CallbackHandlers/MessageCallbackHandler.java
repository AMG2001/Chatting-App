package gov.iti.jets.Service.CallbackHandlers;

import DTO.MessageDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.List;


public class MessageCallbackHandler {
    public MessageCallbackHandler() {
    }
    public void sendMessages(MessageDTO message, List<RemoteCallbackInterface> contacts) {
        try {
            for (RemoteCallbackInterface contact : contacts) {
                contact.recieveMessage(message);
            }
        } catch (RemoteException e) {
            System.out.println("Error Sending message: " + e.getMessage());
            System.out.println("Message " + message.getMessageId() + " Failed to send.");
        }
    }
}
