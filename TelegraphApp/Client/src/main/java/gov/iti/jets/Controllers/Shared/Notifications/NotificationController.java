package gov.iti.jets.Controllers.Shared.Notifications;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class NotificationController {
    @FXML
    private Text notification_content;

    @FXML
    private Text notification_date;

    @FXML
    private ImageView notification_image;

    @FXML
    private Text notification_time;

    @FXML
    private Label notification_title;

    private HBox layout;

    private String title, content, date, time;

    private Image image;
    private FXMLLoader loader;

    public HBox getLayout() {
        return layout;
    }

    public NotificationController(String title, String content, String date, String time, Image image) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.image = image;
        try {
            loader = new FXMLLoader(getClass().getResource("/Notifications/Notification.fxml"));
            loader.setController(this);
            layout = loader.load();
        } catch (IOException e) {
            System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Notification Controller : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Notification Controller Default Constructor called ...");
    }
    @FXML
    public void initialize() {
        notification_title.setText(title);
        notification_content.setText(content);
        notification_date.setText(date);
        notification_time.setText(time);
        notification_image.setFitWidth(100);
        notification_image.setFitHeight(100);
        notification_image.setImage(image);
    }
}

