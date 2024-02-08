package gov.iti.jets.Controllers.Shared.Messages;

import DTO.MessageDTO;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.MessageModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;

public class MessageController {
    @FXML
    private HBox messageLayout;

    @FXML
    private WebView messageView;
    FXMLLoader loader;
    HBox layout;

    public MessageController(MessageDTO messageDTO) {
        if (messageDTO.getSenderPhone() == ClientState.getInstance().getLoggedinUserModel().getUserPhone()) {
            try {
                loader = new FXMLLoader(getClass().getResource("/Messages/sentMessage.fxml"));
                loader.setController(this);
                layout = loader.load();
//                layout.setMinWidth(500);
                layout.setAlignment(Pos.CENTER_RIGHT);
                // Disable editing by setting contenteditable to false on the body tag
                WebEngine webEngine = messageView.getEngine();
                webEngine.executeScript("document.body.contentEditable = 'false';");
//                messageView.setDisable(true);
            } catch (IOException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Messages Controller - Sent : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                loader = new FXMLLoader(getClass().getResource("/Messages/receivedMessage.fxml"));
                loader.setController(this);
                layout = loader.load();
                layout.setMinWidth(500);
                layout.setAlignment(Pos.CENTER_LEFT);
                messageView.setDisable(true);
            } catch (IOException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Messages Controller - Received : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void setMessageContent(String htmlText) {
        messageView.getEngine().loadContent(htmlText);
    }

    public HBox getLayout() {
        return layout;
    }
}
