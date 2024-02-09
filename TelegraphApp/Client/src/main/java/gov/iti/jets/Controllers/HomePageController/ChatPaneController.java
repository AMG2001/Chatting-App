package gov.iti.jets.Controllers.HomePageController;

import DTO.MessageDTO;
import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.MessageService;
import gov.iti.jets.ServiceContext.UserService;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatPaneController {
    @FXML
    private VBox chatArea;

    @FXML
    private ImageView chatImage;

    @FXML
    private Text chatName;

    @FXML
    private ListView<MessageController> lv_chatMessages;

    @FXML
    private HBox message_send_area;
    @FXML
    private VBox pane_chatPane;
    @FXML
    private Text receiverStatus;
    @FXML
    private Circle receiverStatusCircle;
    @FXML
    private TextArea messageArea;
    @FXML
    private Button btn_sendMessage;
    VBox layout;
    FXMLLoader loader;
    ConversationCard contactCardData;
    String clientName, clientPhoneNumber;
    Image clientImage;

    @FXML
    public void initialize() {
        changeListViewCellFactory();
        setSendButtonAction();
        clientName = ClientState.getInstance().getLoggedinUserModel().getUserName();
        clientPhoneNumber = ClientState.getInstance().getLoggedinUserModel().getUserPhone();
        clientImage = ClientState.getInstance().getLoggedinUserModel().getProfilePic();
    }

    private void setSendButtonAction() {
        btn_sendMessage.setOnAction(event -> {
            sendMessage();
        });
    }

    private void bindListViewOnObservableList(ObservableList<MessageController> observableList) {
        lv_chatMessages.itemsProperty().bind(new SimpleListProperty<>(observableList));
    }

    private void changeListViewCellFactory() {
        lv_chatMessages.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(MessageController messageController, boolean empty) {
                super.updateItem(messageController, empty);
                if (empty || messageController == null) {
                    setGraphic(null);
                } else {
                    setGraphic(messageController.getLayout());
                }
            }
        });
    }

    public ChatPaneController(ConversationCard conversationCard) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ChatArea/chatPane.fxml"));
            loader.setController(this);
            layout = loader.load();
            this.contactCardData = conversationCard;
            chatImage.setImage(conversationCard.img_contact.getImage());
            chatName.setText(conversationCard.text_contactName.getText());
            receiverStatus.setText(conversationCard.status_text.getText());
            receiverStatusCircle.setFill(conversationCard.status_circle.getFill());
            if (ClientState.getInstance().conversationsMessagesList.containsKey(conversationCard.getConversationID())) {
                // This mean that the Messages is loaded before from server .
                loadConversationMessagesFromLocal(conversationCard);
            } else {
                // this mean it's first time the chat is opened , then load messages from Server .
                loadChatMessagesFromServer(conversationCard);
            }
        } catch (Exception e) {
            CustomDialogs.showErrorDialog("Error while loading HomePageView : " + e.getMessage());
            System.err.println("Error while loading HomePageView : " + e.getMessage());
        }
    }


    public VBox getLayout() {
        return layout;
    }

    private void loadConversationMessagesFromLocal(ConversationCard conversationCard) {
        lv_chatMessages.setItems(ClientState.getInstance().conversationsMessagesList.get(conversationCard.getConversationID()));
    }

    private void loadChatMessagesFromServer(ConversationCard conversationCard) {
        try {
            // Just for Debugging .
            System.out.println("Conversation ID : " + conversationCard.getConversationID());
            //
            if (MessageService.getInstance().getRemoteService().getAllMessagesForConversation(conversationCard.getConversationID()) == null) {
                System.out.println("Messages of Conversation : " + conversationCard.getConversationID() + " Are Null !!");
            } else {
                if (ClientState.getInstance().conversationsMessagesList.containsKey(conversationCard.getConversationID())) {
                    ClientState.getInstance().conversationsMessagesList.get(conversationCard.getConversationID()).clear();
                } else {
                    // create new conversations messages observable.
                    ObservableList<MessageController> newList = FXCollections.observableArrayList();
                    bindListViewOnObservableList(newList);
                    ClientState.getInstance().conversationsMessagesList.put(conversationCard.getConversationID(), newList);
                    // Load all messages from server then show them on UI .
                    MessageService.getInstance().getRemoteService().getAllMessagesForConversation(conversationCard.getConversationID()).stream().forEach(messageDTO -> {
                        MessageController messageController = new MessageController(messageDTO);
                        // To Store all messages related to it's Conversation in ConversationsMessages Map .
                        ClientState.getInstance().conversationsMessagesList.get(conversationCard.getConversationID()).add(messageController);
                    });

                }
            }
        } catch (RemoteException e) {
            CustomDialogs.showErrorDialog("Error while loading chat messages from server : " + e.getMessage());
        }
    }

    private void sendMessage() {
        if (messageArea.getText().isEmpty()) {
            CustomDialogs.showErrorDialog("You can't leave message area empty !!");
        } else {
            MessageController messageController = new MessageController(clientPhoneNumber, messageArea.getText(), LocalDateTime.now(), clientImage);
            lv_chatMessages.getItems().add(messageController);
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessageBody(messageArea.getText());
            messageDTO.setSenderPhone(clientPhoneNumber);
            messageDTO.setConversationId(contactCardData.getConversationID());
            messageDTO.setTimeStamp(LocalDateTime.now());
            messageArea.clear();
            try {
                MessageService.getInstance().getRemoteService().sendMessage(messageDTO);
                System.out.println("Message Sent Successfully ");
            } catch (RemoteException e) {
                CustomDialogs.showErrorDialog("Error while sending message : " + e.getMessage());
            }
        }

    }

    @FXML
    void showAttachmentsPane(ActionEvent event) {

    }
}
