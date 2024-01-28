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
    public ImageView img_contact;

    @FXML
    public Text text_contactBio;

    @FXML
    public Text text_contactName;
    private Parent loader;
    private ContactCardDataModel controller;

    public Parent getLoader() {
        return loader;
    }

    public ContactCardDataModel() {

    }

    public ContactCardDataModel setComponentAttribute(String name, String bio, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Shared/ContactCard.fxml"));
            loader = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.text_contactName.setText(name);
            controller.text_contactBio.setText(bio);
            controller.img_contact.setImage(image);
            return controller;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
