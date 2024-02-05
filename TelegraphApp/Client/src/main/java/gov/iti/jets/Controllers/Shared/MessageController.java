package gov.iti.jets.Controllers.Shared;

import gov.iti.jets.Model.ClientState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

import java.io.IOException;

public class MessageController {
    @FXML
    private ImageView img_senderImage;

    @FXML
    private HBox message;

    @FXML
    private WebView messageView;

    FXMLLoader loader;
    HBox layout;

    public MessageController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Shared/message.fxml"));
            loader.setController(this);
            layout = loader.load();
        } catch (IOException e) {
            System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading MessageController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        messageView.setDisable(true);
        img_senderImage.setFitHeight(80);
        img_senderImage.setFitWidth(80);
    }

    public HBox getLayout() {
        return layout;
    }

    public void setMessageDetails(String senderPhone, Image senderImage, String messageContent) {
        img_senderImage.setImage(senderImage);
        messageView.getEngine().loadContent(messageContent);
        if (senderPhone == ClientState.getInstance().getLoggedinUserModel().getUserPhone()) {
            // TODO here the position of the image and content will be reversed .
        }
    }
}
