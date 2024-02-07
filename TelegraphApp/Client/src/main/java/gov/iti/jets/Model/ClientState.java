package gov.iti.jets.Model;

import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Controllers.services.ChatBot.ChatBotChatMessageController;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import gov.iti.jets.Model.Requests.RequestSendModel;
import gov.iti.jets.Model.User.ContactModel;
import gov.iti.jets.Model.User.UserModel;
import gov.iti.jets.ServiceContext.RequestService;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.beans.property.SimpleObjectProperty;

import java.rmi.RemoteException;

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

    /*
     *************************************** Messages Observables ********************************
     */
    public ObservableList<ChatBotChatMessageController> chatBotChatMessages;// ✅ Initialized .

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
        contactsList = FXCollections.observableArrayList();
        chatBotChatMessages = FXCollections.observableArrayList();
        Platform.runLater(() -> loadAllRequests());
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

}

