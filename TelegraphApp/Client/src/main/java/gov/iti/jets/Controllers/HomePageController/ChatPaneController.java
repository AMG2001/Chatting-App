package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ChatPaneController {
    @FXML
    private VBox chatArea;

    @FXML
    private ImageView chatImage;

    @FXML
    private Text chatName;

    @FXML
    private ListView<?> lv_chatMessages;

    @FXML
    private HBox message_send_area;
    @FXML
    private VBox pane_chatPane;
    @FXML
    private Text receiverStatus;
    @FXML
    private Circle receiverStatusCircle;
    VBox layout;
    FXMLLoader loader;

    @FXML
    public void initialize() {

    }

    public ChatPaneController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ChatArea/chatPane.fxml"));
            layout = loader.load();
            loader.setController(this);
            System.out.println("Chat Pane Loaded ✅✅");
        } catch (Exception e) {
            System.err.println("Error while loading HomePageView : " + e.getMessage());
        }
    }

    public VBox getLayout() {
        return layout;
    }

    public void setControllerValues(ContactCardDataModel contactCardData) {
        chatImage.setImage(contactCardData.getContactImage());
        chatName.setText(contactCardData.getContactName());
        receiverStatus.setText("online");
        receiverStatusCircle.setFill(Color.GREEN);
    }
}
