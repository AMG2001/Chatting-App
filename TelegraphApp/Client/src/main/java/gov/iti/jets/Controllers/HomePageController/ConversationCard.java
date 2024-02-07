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
            initClassFields(name, image, status);
            text_contactName.setText(this.name.get());
            img_contact.setImage(this.conversationImage.get());
            status_text.setText(this.status.get());
            status_circle.setFill(this.circle.get().getFill());
        } catch (IOException e) {
            System.err.println("###### Error while loading Parameterized Constructor on ContactCardDataModel");
            e.printStackTrace();
        }
    }

    private void initClassFields(String name, Image image, String status) {
        this.conversationID = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.circle = new SimpleObjectProperty<>();
        this.conversationImage = new SimpleObjectProperty<>();
        this.name.set(name);
        this.conversationImage.set(image);
        this.status.set(status);
        Color color = ConversationsServicesClass.setConversationsCircleColor(status);
        Circle circle = new Circle();
        circle.setFill(color);
        this.circle.set(circle);
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
