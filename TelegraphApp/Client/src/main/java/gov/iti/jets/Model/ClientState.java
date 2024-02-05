package gov.iti.jets.Model;

import gov.iti.jets.Controllers.Shared.MessageController;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.beans.property.SimpleObjectProperty;

public class ClientState {
    private static ClientState instance;
    public ObservableList<NotificationModel> notificationsList;
    public ObservableList<RequestReceiveModel> receivedRequestsList;
    ObservableValue<UserModel> loggedinUser;
    public ObservableList<MessageController> openedChatMessagesList;
    String previousChatPhoneNumber;

    private ClientState() {
        loggedinUser = new SimpleObjectProperty<>();
        openedChatMessagesList = FXCollections.observableArrayList();
        notificationsList = FXCollections.observableArrayList();
        receivedRequestsList = FXCollections.observableArrayList();
    }

    public static ClientState getInstance() {
        if (instance == null) {
            instance = new ClientState();
        }
        return instance;
    }

    public void addNotification(NotificationModel notificationModel) {
        notificationsList.add(notificationModel);
    }

    public UserModel getLoggedinUserModel() {
        return loggedinUser.getValue();
    }

    public void setLoggedinUserProperty(UserModel userModel) {
        this.loggedinUser = new SimpleObjectProperty<>(userModel);
    }

    public ObservableList<MessageController> getOpenedChatMessages() {
        return openedChatMessagesList;
    }

}

