package gov.iti.jets.Controllers.Shared.Notifications;

import gov.iti.jets.Model.NotificationModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDateTime;

public class NotificationController {
    @FXML
    private Text notification_content;

    @FXML
    private Text notification_time;

    @FXML
    private Label notification_title;

    private HBox layout;
    private String title, content;
    private LocalDateTime time;
    private FXMLLoader loader;

    public HBox getLayout() {
        return layout;
    }

    public NotificationController(NotificationModel notificationModel) {
        this.title = notificationModel.getType();
        this.content = notificationModel.getBody();
        this.time = notificationModel.getTimeStamp();
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
        notification_time.setText(time.toString());
    }
}

