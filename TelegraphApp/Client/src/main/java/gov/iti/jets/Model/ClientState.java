package gov.iti.jets.Model;

import gov.iti.jets.Controllers.Shared.MessageController;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashMap;

public class ClientState {
    private static ClientState instance;

    public ObservableList<NotificationModel> notificationsList;
    public ObservableList<ReceivedRequestsModel> receivedRequestsList;
    // is used to store logged client .
    ObservableValue<UserModel> loggedinUser;
    public ObservableList<MessageController> openedChatMessagesList;
    //    public HashMap<String, ObservableList<MessageController>> chatsMap;
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

//    public void openChat(String phoneNumber) {
//        if (!chatsMap.containsKey(phoneNumber)) {
//            System.out.println("New Chat Added to ChatMap");
//            chatsMap.put(phoneNumber, FXCollections.observableArrayList());
//        } else {
//            // store previous chat in chats map
//            chatsMap.put(previousChatPhoneNumber, openedChatMessagesList);
//        }
//        // get new chat messages
////        openedChatMessagesList = chatsMap.get(phoneNumber);
////        previousChatPhoneNumber = phoneNumber;
//        // TODO: add message controller to list
//    }

}

