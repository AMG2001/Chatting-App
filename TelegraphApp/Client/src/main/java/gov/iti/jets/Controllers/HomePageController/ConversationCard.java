package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.services.ConversationsServicesClass;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Model.User.ContactModel;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class ConversationCard {
    @FXML
    public ImageView img_contact;

    @FXML
    public Circle status_circle;

    @FXML
    public Label status_text;

    @FXML
    public Text text_contactName;
    private int conversationID;
    private String phoneNumber;
    private HBox layout;
    FXMLLoader loader;

    public int getConversationID() {
        return conversationID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ConversationCard(ContactModel contactModel) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ContactCard.fxml"));
            loader.setController(this);
            layout = loader.load();
            this.conversationID = contactModel.getConversation().getConversationId();
            this.phoneNumber = contactModel.getPhoneNumber();
            this.status_circle.setFill(contactModel.getStatusCircleColor());
            text_contactName.setText(contactModel.getName());
            img_contact.setImage(FileConverter.convert_bytesToImage(contactModel.getProfilepic()));
            status_text.setText(contactModel.getStatus());
        } catch (IOException e) {
            System.err.println("###### Error while loading Parameterized Constructor on ContactCardDataModel");
            e.printStackTrace();
        }
    }

    public HBox getLayout() {
        return layout;
    }

}
