package gov.iti.jets.ServiceContext.callback;

import DTO.*;
import DTO.Group.GroupDTO;
import DTO.Request.RequestRecieveDTO;
import DTO.Request.RequestResponseDTO;
import DTO.User.ContactDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Client;
import gov.iti.jets.Controllers.HomePageController.Attachments.AttachmentsController;
import gov.iti.jets.Controllers.HomePageController.ConversationCard;
import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Controllers.Shared.Notifications.CustomNotifications;
import gov.iti.jets.Controllers.services.ConversationsServicesClass;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Controllers.services.FileSystemUtil;
import gov.iti.jets.Model.AttachmentModel;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.NotificationModel;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import gov.iti.jets.Model.Requests.RequestResponseModel;
import gov.iti.jets.Model.User.ContactModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerCallback extends UnicastRemoteObject implements RemoteCallbackInterface {
    public ServerCallback() throws RemoteException {

    }

    @Override
    public void recieveMessage(MessageDTO message) {
        MessageController messageController = new MessageController(message);
        Platform.runLater(() -> {
            int commingMessageConversationID = message.getConversationId();
            if (ClientState.getInstance().conversationsMessagesList.containsKey(commingMessageConversationID)) {
                // this mean that the Conversation is already in the list .
                // add the message to the list .
                ClientState.getInstance().conversationsMessagesList.get(commingMessageConversationID).add(messageController);
            } else {
                // this mean that the Conversation is not in the list .
                // create new Observable Conversation Messages List .
                ObservableList<MessageController> conversationMessagesList = FXCollections.observableArrayList();
                // add new conversation to the list .
                ClientState.getInstance().conversationsMessagesList.put(commingMessageConversationID, conversationMessagesList);
                // add message into conversation .
                ClientState.getInstance().conversationsMessagesList.get(commingMessageConversationID).add(messageController);
            }
        });
    }

    @Override
    public void recieveAttachment(AttachmentDTO attachment) throws RemoteException {
        AttachmentModel attachmentModel = new AttachmentModel(attachment);
        if (ClientState.getInstance().attachmentsMap.containsKey(attachmentModel.getConversationId())) {
            AttachmentsController attachmentsController = new AttachmentsController(attachmentModel);
            ClientState.getInstance().attachmentsMap.get(attachment.getConversationId()).add(attachmentsController);
            FileSystemUtil.storeByteArrayAsFile(attachment.getAttachment(), attachment.getAttachmentName() + attachment.getAttachmentType());
        } else {
            ObservableList<AttachmentsController> newAttachmentsList = FXCollections.observableArrayList();
            ClientState.getInstance().attachmentsMap.put(attachment.getConversationId(), newAttachmentsList);
            AttachmentsController attachmentsController = new AttachmentsController(attachmentModel);
            ClientState.getInstance().attachmentsMap.get(attachment.getConversationId()).add(attachmentsController);
            FileSystemUtil.storeByteArrayAsFile(attachment.getAttachment(), attachment.getAttachmentName() + attachment.getAttachmentType());
        }
    }

    @Override
    public void recieveNotification(NotificationDTO notification) throws RemoteException { // Done ✅
        NotificationModel notificationModel = new NotificationModel(notification);
        // Show Notification on System .
        Platform.runLater(() -> CustomNotifications.showCustomNotification(notificationModel));
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
                ClientState.getInstance().sentRequestsList.removeIf(requestSendModel -> requestSendModel.getSenderPhone().equals(requestResponseModel.getSenderPhone()));
            });
        }
    }

    @Override
    public void addContact(ContactDTO newContact) throws RemoteException {
        ContactModel contactModel = new ContactModel(newContact);
        Platform.runLater(() -> ClientState.getInstance().contactsList.add(contactModel));
        Platform.runLater(() -> {
            ClientState.getInstance().conversationsList.add(new ConversationCard(contactModel));
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
        Platform.runLater(() -> {
            ClientState.getInstance().contactsList.stream()
                    .filter(contactModel -> contactModel.getPhoneNumber().equals(phone))
                    .findFirst()
                    .ifPresent(contactModel -> {
                        contactModel.setStatus(status);
                        Color newColor = ConversationsServicesClass.setConversationsCircleColor(status);
                        contactModel.statusCircleColorProperty().set(newColor);
                    });
            ClientState.getInstance().conversationsList.stream()
                    .filter(conversationCard -> conversationCard.getPhoneNumber().equals(phone))
                    .findFirst()
                    .ifPresent(conversationCard -> {
                        conversationCard.status_text.setText(status);
                        conversationCard.status_circle.setFill(ConversationsServicesClass.setConversationsCircleColor(status));
                    });
        });
    }


}
