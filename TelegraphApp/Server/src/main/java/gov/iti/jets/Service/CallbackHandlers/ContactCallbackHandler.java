package gov.iti.jets.Service.CallbackHandlers;

import DTO.User.ContactDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.List;

public class ContactCallbackHandler {

    public void updateContactName(String phone, String newName, List<RemoteCallbackInterface> contacts) {
        try {
            for (RemoteCallbackInterface contact : contacts) {
                contact.updateContactName(phone, newName);
            }
        } catch (RemoteException e) {
            System.out.println("Error Updating Username for Contact: " + e.getMessage());
            System.out.println("Contact Name" + newName + " failed to update");
        }

    }

    public void updateContactPic(String phone, byte[] newPic, List<RemoteCallbackInterface> contacts) {
        try {
            for (RemoteCallbackInterface contact : contacts) {
                contact.updateContactPic(phone, newPic);
            }
        } catch (RemoteException e) {
            System.out.println("Error Updating Username for Contact: " + e.getMessage());
            System.out.println("Profile Pic failed to update for " + phone);
        }

    }

    public void updateContactStatus(String phone, String newStatus, List<RemoteCallbackInterface> contacts) {
        try {
            for (RemoteCallbackInterface contact : contacts) {
                contact.updateContactStatus(phone, newStatus);
            }
        } catch (RemoteException e) {
            System.out.println("Error Updating Status for Contact: " + e.getMessage());
            System.out.println("Status " + newStatus + " failed to update for user " + phone);
        }
    }

    public void addContact(ContactDTO newContact, RemoteCallbackInterface contact) {
        try {
            contact.addContact(newContact);
        } catch (RemoteException e) {
            System.out.println("Error adding new Contact : " + e.getMessage());
            System.out.println("Contact Name" + newContact.getName() + " failed to be added");
        }

    }

}
