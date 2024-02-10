package gov.iti.jets.Controllers.Shared.Messages;

import DTO.MessageDTO;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.MessageModel;
import gov.iti.jets.Model.User.ContactModel;
import gov.iti.jets.ServiceContext.MessageService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.time.LocalDateTime;

public class MessageController {
    @FXML
    private Circle userImage;
    @FXML
    private Label userMessage;
    @FXML
    private Label messageTime;
    @FXML
    private HBox userMessageBox;
    FXMLLoader loader;
    HBox layout;

    ImagePattern senderImage, recieverImage;

    public MessageController(String senderPhoneNumber, String content, LocalDateTime localDateTime, Image senderImage) {
        if (senderPhoneNumber.equals(ClientState.getInstance().getLoggedinUserModel().getUserPhone())) {
            try {
                loader = new FXMLLoader(getClass().getResource("/Messages/sentMessage.fxml"));
                loader.setController(this);
                layout = loader.load();
                this.userMessage.setText(content);
                this.messageTime.setText(getTimeFormatted(localDateTime));
                this.userMessage.setWrapText(true);
                this.userImage.setFill(new ImagePattern(senderImage));
            } catch (IOException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Messages Controller - Sent : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                loader = new FXMLLoader(getClass().getResource("/Messages/receivedMessage.fxml"));
                loader.setController(this);
                layout = loader.load();
                this.userMessage.setText(content);
                this.messageTime.setText(getTimeFormatted(localDateTime));
                this.userMessage.setWrapText(true);
                this.userImage.setFill(new ImagePattern(senderImage));
            } catch (IOException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Messages Controller - Received : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public MessageController(MessageDTO messageDTO) {
        if (messageDTO.getSenderPhone().equals(ClientState.getInstance().getLoggedinUserModel().getUserPhone())) {
            try {
                loader = new FXMLLoader(getClass().getResource("/Messages/sentMessage.fxml"));
                loader.setController(this);
                layout = loader.load();
                this.userMessage.setText(messageDTO.getMessageBody());
                this.messageTime.setText(getTimeFormatted(messageDTO.getTimeStamp()));
                this.userMessage.setWrapText(true);
                this.userImage.setFill(new ImagePattern(ClientState.getInstance().getLoggedinUserModel().getProfilePic()));
            } catch (IOException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Messages Controller - Sent : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                loader = new FXMLLoader(getClass().getResource("/Messages/receivedMessage.fxml"));
                loader.setController(this);
                layout = loader.load();
                getContactImageByPhoneNumber(messageDTO.getSenderPhone());
                this.userMessage.setText(messageDTO.getMessageBody());
                this.messageTime.setText(getTimeFormatted(messageDTO.getTimeStamp()));
                this.userMessage.setWrapText(true);
                this.userImage.setFill(recieverImage);
            } catch (IOException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Messages Controller - Received : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void getContactImageByPhoneNumber(String phoneNumber) {
        for (ContactModel contact : ClientState.getInstance().contactsList) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                recieverImage = new ImagePattern(FileConverter.convert_bytesToImage(contact.getProfilepic()));
                break;
            }
        }
    }

    private String getTimeFormatted(LocalDateTime localDateTime) {
        return localDateTime.getHour() + ":" + localDateTime.getMinute();
    }

    public HBox getLayout() {
        return layout;
    }
}
