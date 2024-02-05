package gov.iti.jets.Controllers.Shared.Notifications;

import gov.iti.jets.Model.NotificationModel;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class CustomNotifications {

    public static void showCustomNotification(NotificationModel notificationModel) {
        Platform.runLater(() -> {
            Notifications.create()
                    .title(notificationModel.getType())
                    .text(notificationModel.getBody() + "\n" + notificationModel.getTimeStamp()).hideAfter(new Duration(3000)).position(Pos.BOTTOM_RIGHT).darkStyle()
                    .showInformation();
        });

    }
}
