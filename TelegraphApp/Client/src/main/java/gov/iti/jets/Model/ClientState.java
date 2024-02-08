package gov.iti.jets.Model;

import gov.iti.jets.Controllers.HomePageController.ConversationCard;
import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Controllers.services.ChatBot.ChatBotChatMessageController;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import gov.iti.jets.Model.Requests.RequestSendModel;
import gov.iti.jets.Model.User.ContactModel;
import gov.iti.jets.Model.User.UserModel;
import gov.iti.jets.ServiceContext.RequestService;
import gov.iti.jets.ServiceContext.UserService;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.beans.property.SimpleObjectProperty;

import java.rmi.RemoteException;
import java.util.HashMap;

public class ClientState {
    /*
     *************************************** Current Client Observables ********************************
     */
    public ObservableValue<UserModel> loggedinUser; // ✅ Initialized .
    /*
     ************************************** Requests Observables **************************************
     */
    public ObservableList<RequestReceiveModel> receivedRequestsList; // ✅ Initialized .
    public ObservableList<RequestSendModel> sentRequestsList;

    /*
     *************************************** Conversations Observables ********************************
     */
    public ObservableList<ConversationCard> conversationsList = FXCollections.observableArrayList();

    /*
     *************************************** Messages Observables ********************************
     */
    public ObservableList<ChatBotChatMessageController> chatBotChatMessages;// ✅ Initialized .
    public HashMap<Integer, ObservableList<MessageController>> conversationsMessagesList = new HashMap<>();

    /*
     *************************************** Contacts - Groups  Observables ********************************
     */
    public ObservableList<ContactModel> contactsList; // ✅ Initialized .
    /*
     *************************************** Notifications Observables ********************************
     */
    public ObservableList<NotificationModel> notificationsList;// ✅ Initialized .

    public ObservableList<MessageController> openedChatMessagesList;
    String previousChatPhoneNumber;

    /*
     *************************************** Singleton Pattern ***************************************
     */
    private static ClientState instance;

    private ClientState() {
        // User Functions Initialization .
        loggedinUser = new SimpleObjectProperty<>();
        openedChatMessagesList = FXCollections.observableArrayList();
        // Notifications
        notificationsList = FXCollections.observableArrayList();
        // Requests Initialization .
        receivedRequestsList = FXCollections.observableArrayList();
        // Contacts initialization .
        contactsList = FXCollections.observableArrayList();
        chatBotChatMessages = FXCollections.observableArrayList();
        Platform.runLater(() -> loadAllRequests());
        Platform.runLater(() -> loadAllContacts());
        // TODO load all Groups .
    }

    public static ClientState getInstance() {
        if (instance == null) {
            instance = new ClientState();
        }
        return instance;
    }

    /*
    ----------------------------------------------------------------------------------------------
    ----------------------------------------------------------------------------------------------
                                          Current User Methods
    ----------------------------------------------------------------------------------------------
    ----------------------------------------------------------------------------------------------
     */
    public UserModel getLoggedinUserModel() {
        return loggedinUser.getValue();
    }

    public void setLoggedinUserProperty(UserModel userModel) {
        this.loggedinUser = new SimpleObjectProperty<>(userModel);
    }

    /*
    ----------------------------------------------------------------------------------------------
    ----------------------------------------------------------------------------------------------
                                      Requests Observable Methods
    ----------------------------------------------------------------------------------------------
    ----------------------------------------------------------------------------------------------
     */
    public void loadAllRequests() {
        try {
            RequestService.getInstance().getRemoteService().getAllRequest(loggedinUser.getValue().getUserPhone()).stream().forEach(requestRecieveDTO -> {
                RequestReceiveModel requestReceiveModel = new RequestReceiveModel(requestRecieveDTO);
                receivedRequestsList.add(requestReceiveModel);
            });
        } catch (RemoteException e) {
            System.out.println("Error while loading all requests : " + e.getMessage());
        }
    }

    /*
    ----------------------------------------------------------------------------------------------
    ----------------------------------------------------------------------------------------------
                                      Notification Observable Methods
    ----------------------------------------------------------------------------------------------
    ----------------------------------------------------------------------------------------------
     */
    public void addNotification(NotificationModel notificationModel) {
        notificationsList.add(notificationModel);
    }

    public ObservableList<MessageController> getOpenedChatMessages() {
        return openedChatMessagesList;
    }

    /*
        ----------------------------------------------------------------------------------------------
        ----------------------------------------------------------------------------------------------
                                          Conversations Observable Methods
        ----------------------------------------------------------------------------------------------
        ----------------------------------------------------------------------------------------------
         */
    private void loadAllContacts() {
        try {
            UserService.getInstance().getRemoteService().getContacts(loggedinUser.getValue().getUserPhone()).stream().forEach(contactDTO -> {
                ContactModel contactModel = new ContactModel(contactDTO);
                contactsList.add(contactModel);
                conversationsList.add(new ConversationCard(contactModel.getConversation().getConversationId(), contactModel.getPhoneNumber(), contactModel.getName(), FileConverter.convert_bytesToImage(contactModel.getProfilepic()), contactModel.getStatus(), contactModel.getStatusCircleColor()));
            });
        } catch (RemoteException e) {
            CustomDialogs.showErrorDialog("Error while loading all contacts : " + e.getMessage());
        }
    }

    // TODO Implement Load Groups .
//    private void loadAllGroups() {
//        try {
//            UserService.getInstance().getRemoteService().getGroups(loggedinUser.getValue().getUserPhone()).stream().forEach(contactDTO -> {
//                Grou contactModel = new ContactModel(contactDTO);
//                contactsList.add(contactModel);
//                conversationsList.add(new ConversationCard(contactModel.getConversation().getConversationId(), contactModel.getName(), FileConverter.convert_bytesToImage(contactModel.getProfilepic()), contactModel.getStatus()));
//            });
//        } catch (RemoteException e) {
//            CustomDialogs.showErrorDialog("Error while loading all contacts : " + e.getMessage());
//        }
//    }
}

