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
    ObservableValue<UserModel> loggedinUser;
    ObservableList<UserModel> contacts;
    ObservableList<ConversationModel> conversations;
    ObjectProperty<ConversationModel> currentConversation;
    ObservableList<MessageController> openedChatMessagesList;
    HashMap<String, ObservableList<MessageController>> chatsMap;

    private ClientState() {
        loggedinUser = new SimpleObjectProperty<>();
        contacts = FXCollections.observableArrayList();
        conversations = FXCollections.observableArrayList();
        currentConversation = new SimpleObjectProperty<>();
        openedChatMessagesList = FXCollections.observableArrayList();
        chatsMap = new HashMap<>();
    }

    public static ClientState getInstance() {
        if (instance == null) {
            instance = new ClientState();
        }
        return instance;
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

    public void openChat(String phoneNumber) {
        if (!chatsMap.containsKey(phoneNumber)) {
            System.out.println("New Chat Added to ChatMap");
            chatsMap.put(phoneNumber, FXCollections.observableArrayList());
        }
        openedChatMessagesList = chatsMap.get(phoneNumber);
        System.out.println("Retreive chat of Number : " + phoneNumber);
        // TODO: add message controller to list
    }
}

