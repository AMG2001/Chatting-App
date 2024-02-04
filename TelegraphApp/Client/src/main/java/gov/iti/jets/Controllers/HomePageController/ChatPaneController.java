package gov.iti.jets.Controllers.HomePageController;

import com.gluonhq.richtextarea.RichTextArea;
import gov.iti.jets.Client;
import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import gov.iti.jets.Controllers.Shared.MessageController;
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
import javafx.scene.layout.Pane;
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
    ContactCardDataModel contactCardData;
    String clientName, clientPhoneNumber;
    Image clientImage;

    @FXML
    public void initialize() {
        lv_chatMessages.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().getOpenedChatMessages()));
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
        btn_sendMessage.setOnAction(event -> {
            sendMessage();
        });
        clientName = ClientState.getInstance().getLoggedinUserModel().getUserName();
        clientPhoneNumber = ClientState.getInstance().getLoggedinUserModel().getUserPhone();
        clientImage = ClientState.getInstance().getLoggedinUserModel().getProfilePic();
    }

    public ChatPaneController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ChatArea/chatPane.fxml"));
            loader.setController(this);
            layout = loader.load();
            System.out.println("Chat Pane Loaded ✅✅");
        } catch (Exception e) {
            System.err.println("Error while loading HomePageView : " + e.getMessage());
        }
    }


    public VBox getLayout() {
        return layout;
    }

    private void sendMessage() {
        if (messageArea.getHtmlText().length() != 0) {
            MessageController messageController = new MessageController();
            messageController.setMessageDetails(clientPhoneNumber, clientImage, messageArea.getHtmlText());
            ClientState.getInstance().openedChatMessagesList.add(messageController);
        }
    }

    public void setControllerValues(ContactCardDataModel contactCardData) {
        this.contactCardData = contactCardData;
        chatImage.setImage(contactCardData.getContactImage());
        chatName.setText(contactCardData.getContactName());
        receiverStatus.setText("online");
        receiverStatusCircle.setFill(Color.GREEN);
    }
}
