package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.services.ConversationsServicesClass;
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
    int conversationID;
    private HBox layout;
    FXMLLoader loader;

    public ConversationCard(int conversationID, String name, Image image, String status) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ContactCard.fxml"));
            loader.setController(this);
            layout = loader.load();
            this.conversationID = conversationID;
            setStatusCircleColor(status);
            text_contactName.setText(name);
            img_contact.setImage(image);
            status_text.setText(status);
        } catch (IOException e) {
            System.err.println("###### Error while loading Parameterized Constructor on ContactCardDataModel");
            e.printStackTrace();
        }
    }

    private void setStatusCircleColor(String status) {
        Color color = ConversationsServicesClass.setConversationsCircleColor(status);
        this.status_circle.setFill(color);
    }

    public HBox getLayout() {
        return layout;
    }

}
