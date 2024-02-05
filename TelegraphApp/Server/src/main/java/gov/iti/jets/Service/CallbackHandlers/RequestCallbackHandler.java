package gov.iti.jets.Service.CallbackHandlers;

import DTO.RecievedRequestDTO;
import DTO.SentRequestDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;

public class RequestCallbackHandler {
    public void updateRequest(SentRequestDTO request, RemoteCallbackInterface reciever) {
        try {
            reciever.updateRequest(request);
        } catch (RemoteException ex) {
            System.out.println("Request Failed to update between "
                    +request.getSenderPhone()+" "+request.getReceiverPhone());
            System.out.println("Error message : "+ex.getMessage());
        }
    }
    public void sendRequest(RecievedRequestDTO request, RemoteCallbackInterface reciever){
        try{
            reciever.recieveRequest(request);
        } catch (RemoteException e) {
            System.out.println("Request failed to send between "
                    +request.getSenderPhone()+" "+request.getReceiverPhone());
            System.out.println("ErrorMessage : "+e.getMessage());
        }
    }
}
