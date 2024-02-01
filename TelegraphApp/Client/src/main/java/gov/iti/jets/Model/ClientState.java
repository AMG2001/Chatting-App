package gov.iti.jets.Model;

import javafx.beans.value.ObservableObjectValue;
import javafx.collections.ObservableList;

public class ClientState {
    ObservableObjectValue<UserModel> loggedinUser;
    ObservableList<UserModel> contacts;

    //TODO There has be a conversation object for each contact - the messages can be loaded later
    ObservableList<ConversationModel> conversations;
    ObservableObjectValue<ConversationModel> currentConversation;

}
