package gov.iti.jets.Controllers.Shared.Notifications;

import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NotificationsListViewController {
    @FXML
    public ListView<NotificationController> notifications_listView;
    private ObservableList<NotificationController> notificationsList = FXCollections.observableArrayList();

    private FXMLLoader loader;

    @FXML
    public void initialize() {
        System.out.println("✅✅✅✅✅✅ Notifications List view initialized");
        // Bind listview on Observable ArrayList .
        notifications_listView.itemsProperty().bind(new SimpleListProperty<>(notificationsList));
        // Set the cell factory
        notifications_listView.setCellFactory(param -> new ListCell<NotificationController>() {
            @Override
            protected void updateItem(NotificationController notificationController, boolean empty) {
                super.updateItem(notificationController, empty);
                if (empty || notificationController == null) {
                    setGraphic(null);
                } else {
                    // Use the layout of the notificationController as the graphic
                    setGraphic(notificationController.getLayout());
                }
            }
        });

        // TODO - fetch contacts from server .
        for (int i = 0; i < 10; i++) {
            NotificationController contactCardObjOnline = new NotificationController("Notification " + i, "This is the notification body content .", "" + LocalDate.now(), getCurrentTimeFormatted(), new Image(getClass().getResource("/assets/images/telegraph.png").toExternalForm()));
            notificationsList.add(contactCardObjOnline);
        }
    }

    private String getCurrentTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }

    public ListView<NotificationController> getNotifications_listView() {
        return notifications_listView;
    }

    public NotificationsListViewController() {
        loader = new FXMLLoader(getClass().getResource("/Notifications/NotificationsListView.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading NotificationsListViewController : " + e.getMessage());
            e.printStackTrace();
        }
    }


}
