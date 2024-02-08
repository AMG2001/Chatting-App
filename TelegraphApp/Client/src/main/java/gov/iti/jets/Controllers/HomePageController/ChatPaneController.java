package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Model.ClientState;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

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
    private HTMLEditor messageArea;
    @FXML
    private Button btn_sendMessage;
    VBox layout;
    FXMLLoader loader;
    ConversationCard contactCardData;
    String clientName, clientPhoneNumber;
    Image clientImage;

    @FXML
    public void initialize() {
        bindListViewOnObservableList();
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

    private void bindListViewOnObservableList() {
        lv_chatMessages.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().getOpenedChatMessages()));
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
            System.out.println("Chat Pane Loaded ✅✅");
            this.contactCardData = conversationCard;
            chatImage.setImage(conversationCard.img_contact.getImage());
            chatName.setText(conversationCard.text_contactName.getText());
            receiverStatus.setText(conversationCard.status_text.getText());
            receiverStatusCircle.setFill(conversationCard.status_circle.getFill());
        } catch (Exception e) {
            System.err.println("Error while loading HomePageView : " + e.getMessage());
        }
    }


    public VBox getLayout() {
        return layout;
    }

    private void sendMessage() {
        if (messageArea.getHtmlText().length() != 0) {
            MessageController messageController = new MessageController(ClientState.getInstance().getLoggedinUserModel().getUserPhone());
            messageController.setMessageContent(messageArea.getHtmlText());
            ClientState.getInstance().openedChatMessagesList.add(messageController);
            messageArea.setHtmlText("");
        }
    }
}
