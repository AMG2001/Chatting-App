package gov.iti.jets.ServiceContext.callback;

import DTO.*;
import DTO.Group.GroupDTO;
import DTO.Request.RequestRecieveDTO;
import DTO.Request.RequestResponseDTO;
import DTO.Request.RequestSendDTO;
import DTO.User.ContactDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Controllers.Shared.Notifications.CustomNotifications;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.NotificationModel;
import gov.iti.jets.Model.ReceivedRequestsModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerCallback extends UnicastRemoteObject implements RemoteCallbackInterface {
    public ServerCallback() throws RemoteException {

    }

    @Override
    public void recieveMessage(MessageDTO message) {
        //TODO Add the message to the CONVERSATION observable object

    }

    @Override
    public void recieveAttachment(AttachmentDTO attachment) throws RemoteException {

    }

    @Override
    public void recieveNotification(NotificationDTO notification) throws RemoteException { // Done ✅
        NotificationModel notificationModel = new NotificationModel(notification);
        // Show Notification on System .
        CustomNotifications.showCustomNotification(notificationModel);
        // Store Notification in Notifications List.
        ClientState.getInstance().addNotification(notificationModel);
    }

    @Override
    public void recieveRequest(RequestRecieveDTO request) throws RemoteException {
        ReceivedRequestsModel receivedRequestsModel = new ReceivedRequestsModel(request);
        ClientState.getInstance().receivedRequestsList.add(receivedRequestsModel);
    }

    @Override
    public void updateRequest(RequestResponseDTO request) throws RemoteException {

    }

    @Override
    public void addContact(ContactDTO newContact) throws RemoteException {

    }

    @Override
    public void addGroup(GroupDTO newGroup) throws RemoteException {

    }


    @Override
    public void updateContactName(String phone, String name) throws RemoteException {

    }

    @Override
    public void updateContactPic(String phone, byte[] picture) throws RemoteException {

    }

    @Override
    public void updateContactStatus(String phone, String status) throws RemoteException {

    }
}
