package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.CustomEnums;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class ConversationCard {
    @FXML
    private ImageView img_contact;

    @FXML
    private Circle status_circle;

    @FXML
    private Label status_text;

    @FXML
    private Text text_contactName;

    private HBox layout;
    FXMLLoader loader;
    private IntegerProperty conversationID;
    private StringProperty name, status;
    private ObjectProperty<Circle> circle;
    private ObjectProperty<Image> conversationImage;

    public ConversationCard(int conversationID, String name, Image image, String status) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ContactCard.fxml"));
            loader.setController(this);
            layout = loader.load();
            initClassFields();
            text_contactName.setText(name);
            img_contact.setImage(image);
            showStatus(this.status.get());
        } catch (IOException e) {
            System.err.println("###### Error while loading Parameterized Constructor on ContactCardDataModel");
            e.printStackTrace();
        }
    }

    private void initClassFields() {
        this.conversationID = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.circle = new SimpleObjectProperty<>();
        this.conversationImage = new SimpleObjectProperty<>();

    }

    private void showStatus(String status) {
        if (status == CustomEnums.UserStatus_ONLINE) {
            this.status.set(CustomEnums.UserStatus_ONLINE);
            Circle circle = new Circle();
            circle.setFill(Color.GREEN);
            this.circle.set(circle);
        } else if (status == CustomEnums.UserStatus_OFFLINE) {
            this.status.set(CustomEnums.UserStatus_OFFLINE);
            Circle circle = new Circle();
            circle.setFill(Color.GRAY);
            this.circle.set(circle);
        } else if (status == CustomEnums.UserStatus_AWAY) {
            this.status.set(CustomEnums.UserStatus_AWAY);
            Circle circle = new Circle();
            circle.setFill(Color.ORANGE);
            this.circle.set(circle);
        } else if (status == CustomEnums.UserStatus_BUSY) {
            this.status.set(CustomEnums.UserStatus_BUSY);
            Circle circle = new Circle();
            circle.setFill(Color.RED);
            this.circle.set(circle);
        }
    }

    public IntegerProperty conversationIDProperty() {
        return conversationID;
    }

    public StringProperty getName() {
        return name;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Circle getCircle() {
        return circle.get();
    }

    public ObjectProperty<Circle> circleProperty() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle.set(circle);
    }

    public ObjectProperty<Image> conversationImageProperty() {
        return conversationImage;
    }

    public HBox getLayout() {
        return layout;
    }

}
