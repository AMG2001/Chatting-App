package gov.iti.jets.Controllers.Shared.ContactCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ContactCardDataModel {
    @FXML
    private ImageView img_contact;

    @FXML
    private Text text_contactBio;

    @FXML
    private Text text_contactName;

    private Parent layout;

    private ContactCardDataModel controller;

    public ContactCardDataModel() {

    }

    public ContactCardDataModel(String name, String bio, Image image) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shared/ContactCard.fxml"));
            layout = loader.load();
            controller = loader.getController();
            controller.text_contactName.setText(name);
            controller.text_contactBio.setText(bio);
            controller.img_contact.setImage(image);
        } catch (IOException e) {
            System.err.println("###### Error while loading Parameterized Constructor on ContactCardDataModel");
            e.printStackTrace();
        }
    }

    public Parent getLayout() {
        return layout;
    }

    public ContactCardDataModel getController() {
        return controller;
    }

    public Image getContactImage() {
        return img_contact.getImage();
    }

    public String getContactBio() {
        return text_contactBio.getText();
    }

    public String getContactName() {
        return text_contactName.getText();
    }
}
