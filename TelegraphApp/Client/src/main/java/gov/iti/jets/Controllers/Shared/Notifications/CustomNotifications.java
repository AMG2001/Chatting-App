package gov.iti.jets.Controllers.Shared.Notifications;

import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class CustomNotifications {

    public static void showCustomNotification(String title, String content) {
        Notifications.create()
                .title(title)
                .text(content).hideAfter(new Duration(3000)).position(Pos.BOTTOM_RIGHT).darkStyle()
                .showInformation();
    }
}
