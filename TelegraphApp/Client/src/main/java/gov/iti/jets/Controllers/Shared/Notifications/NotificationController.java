package gov.iti.jets.Controllers.Shared.Notifications;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Parent layout;

    private String title, content, date, time;

    public Parent getLayout() {
        return layout;
    }

    public NotificationController(String title, String content, String date, String time) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Notifications/Notification.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            layout = loader.load();
            notification_title.setText(title);
            notification_content.setText(content);
            notification_date.setText(date);
            notification_time.setText(time);
            notification_image.setImage(new Image(getClass().getResource("/assets/images/telegraph.png").toExternalForm()));

        } catch (IOException e) {
            System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Notification Controller : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
