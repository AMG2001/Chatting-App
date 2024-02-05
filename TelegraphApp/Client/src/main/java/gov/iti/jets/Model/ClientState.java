package gov.iti.jets.Model;

import gov.iti.jets.Controllers.Shared.MessageController;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import gov.iti.jets.Model.Requests.RequestSendModel;
import gov.iti.jets.ServiceContext.RequestService;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.beans.property.SimpleObjectProperty;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientState {
    /*
     *************************************** Current Client Observables ********************************
     */
    ObservableValue<UserModel> loggedinUser;
    /*
     ************************************** Requests Observables **************************************
     */
    public ObservableList<RequestReceiveModel> receivedRequestsList;
    public ObservableList<RequestSendModel> sentRequestsList;

    /*
     *************************************** Conversations Observables ********************************
     */

    /*
     *************************************** Notifications Observables ********************************
     */
    public ObservableList<NotificationModel> notificationsList;

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

