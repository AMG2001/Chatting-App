package gov.iti.jets.Controllers.services.ChatBot;

import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Model.ClientState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChatBotChatMessageController {
    @FXML
    private HBox messageAligner;

    @FXML
    private Label messageContent;

    FXMLLoader loader;
    HBox layout;
    String senderPhoneNumber;

    public ChatBotChatMessageController(String senderPhoneNumber) {
        try {
            loader = new FXMLLoader(getClass().getResource("/ChatBot/ChatbotChatMessage.fxml"));
            loader.setController(this);
            layout = loader.load();
            this.senderPhoneNumber = senderPhoneNumber;
            messageContent.setStyle("-fx-text-fill: white;");
            if (senderPhoneNumber.equals(ClientState.getInstance().getLoggedinUserModel().getUserPhone())) {
                messageAligner.setAlignment(Pos.CENTER_RIGHT);
                messageContent.setStyle("-fx-text-fill: white;-fx-background-color: #2272E5");
            } else {
                messageAligner.setAlignment(Pos.CENTER_LEFT);
                messageContent.setStyle("-fx-text-fill: white;-fx-background-color: #FC6736");

            }
        } catch (IOException e) {
            CustomDialogs.showErrorDialog("Error while Loading Chatbot message Controller : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void setMessageDetails(String content) {
        messageContent.setText(content);

    }

    public HBox getLayout() {
        return layout;
    }
}
