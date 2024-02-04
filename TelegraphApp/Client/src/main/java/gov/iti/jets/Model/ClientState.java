package gov.iti.jets.Model;

import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ClientState {
    private static ClientState instance;
    ObservableValue<UserModel> loggedinUser;
    ObservableList<UserModel> contacts;
    ObservableList<ConversationModel> conversations;
    ObjectProperty<ConversationModel> currentConversation;

    private ClientState() {
        loggedinUser = new SimpleObjectProperty<>();
        contacts = FXCollections.observableArrayList();
        conversations = FXCollections.observableArrayList();
        currentConversation = new SimpleObjectProperty<>();
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
}

