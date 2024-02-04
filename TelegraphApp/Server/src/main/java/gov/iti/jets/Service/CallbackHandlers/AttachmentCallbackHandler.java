package gov.iti.jets.Service.CallbackHandlers;

import DTO.AttachmentDTO;
import DTO.MessageDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.List;

public class AttachmentCallbackHandler {


    public void sendMessages(AttachmentDTO attachment, List<RemoteCallbackInterface> contacts) {
        try {
            for (RemoteCallbackInterface contact : contacts) {
                contact.recieveAttachment(attachment);
            }
        } catch (RemoteException e) {
            System.out.println("Error Sending attachment: " + e.getMessage());
            System.out.println("Attachment "+attachment.getAttachmentName()+" Failed to send.");
        }
    }

    public void sendMessageToContact(AttachmentDTO attachment, RemoteCallbackInterface contact) {
        try {
            contact.recieveAttachment(attachment);
        } catch (RemoteException ex) {
            System.out.println("Error Sending attachment: " + ex.getMessage());
            System.out.println("Attachment " + attachment.getAttachmentName() + " Failed to send.");
        }
    }
}
