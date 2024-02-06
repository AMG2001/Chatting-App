package gov.iti.jets.Controllers.Shared.Messages;

import gov.iti.jets.Model.ClientState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

import java.io.IOException;

public class ReceivedMessageController {
    @FXML
    private HBox messageLayout;

    @FXML
    private WebView messageView;

    FXMLLoader loader;
    HBox layout;

    public ReceivedMessageController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Messages/receivedMessage.fxml"));
            loader.setController(this);
            layout = loader.load();
        } catch (IOException e) {
            System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Received Messages Controller : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public HBox getLayout() {
        return layout;
    }

    public void setMessageDetails(String senderPhone, Image senderImage, String messageContent) {
        messageView.getEngine().loadContent(messageContent);
        if (senderPhone == ClientState.getInstance().getLoggedinUserModel().getUserPhone()) {
            // TODO here the position of the image and content will be reversed .
        }
    }
}
