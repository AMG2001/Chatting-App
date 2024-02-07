package gov.iti.jets.ServiceContext.callback;

import DTO.*;
import DTO.Group.GroupDTO;
import DTO.Request.RequestRecieveDTO;
import DTO.Request.RequestResponseDTO;
import DTO.User.ContactDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Controllers.HomePageController.ConversationCard;
import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.Shared.Notifications.CustomNotifications;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.NotificationModel;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import gov.iti.jets.Model.Requests.RequestResponseModel;
import gov.iti.jets.Model.User.ContactModel;
import javafx.application.Platform;

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
        RequestReceiveModel receivedRequestsModel = new RequestReceiveModel(request);
        Platform.runLater(() -> ClientState.getInstance().receivedRequestsList.add(receivedRequestsModel));
    }

    @Override
    public void updateRequest(RequestResponseDTO request) throws RemoteException {
        // if the Requests Accepted .them remove the request and add the user in
        RequestResponseModel requestResponseModel = new RequestResponseModel(request);
        if (requestResponseModel.getRequestStatus() == CustomEnums.RequestStatus_ACCEPTED || requestResponseModel.getRequestStatus() == CustomEnums.RequestStatus_DENIED) {
            Platform.runLater(() -> {
                ClientState.getInstance().sentRequestsList.removeIf(requestSendModel -> requestSendModel.getSenderPhone() == requestResponseModel.getSenderPhone());
            });
        }
    }

    @Override
    public void addContact(ContactDTO newContact) throws RemoteException {
        ContactModel contactModel = new ContactModel(newContact);
        Platform.runLater(() -> ClientState.getInstance().contactsList.add(contactModel));
        Platform.runLater(() -> {
            ClientState.getInstance().conversationsList.add(
                    new ConversationCard(
                            contactModel.getConversation().getConversationId(),
                            contactModel.getName(),
                            FileConverter.convert_bytesToImage(contactModel.getProfilepic()),
                            contactModel.getStatus()));
        });
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
