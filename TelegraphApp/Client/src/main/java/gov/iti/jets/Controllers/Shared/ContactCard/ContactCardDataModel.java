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

    private HBox layout;
    FXMLLoader loader;
    private ContactCardDataModel controller;
    private String phoneNumber;

    public ContactCardDataModel() {

    }

    public ContactCardDataModel(String name, String bio, String phoneNumber, Image image) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Shared/ContactCard.fxml"));
            loader.setController(this);
            layout = loader.load();
            text_contactName.setText(name);
            text_contactBio.setText(bio);
            img_contact.setImage(image);
            this.phoneNumber = phoneNumber;
        } catch (IOException e) {
            System.err.println("###### Error while loading Parameterized Constructor on ContactCardDataModel");
            e.printStackTrace();
        }
    }

    public HBox getLayout() {
        return layout;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
