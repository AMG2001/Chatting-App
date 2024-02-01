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

public class NotificationsListViewController {
    @FXML
    public ListView<NotificationController> notifications_listView;
    private ObservableList<NotificationController> notificationsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind listview on Observable ArrayList .
        notifications_listView.itemsProperty().bind(new SimpleListProperty<>(notificationsList));
        // TODO - fetch contacts from server .
        System.out.println("Data Online & Offline Contact ..............");
        for (int i = 0; i < 10; i++) {
            NotificationController contactCardObjOnline = new NotificationController("Notification " + i, "This is the notification body content .", "Date", "Time");
            notificationsList.add(contactCardObjOnline);
        }
        notifications_listView.setCellFactory(param -> new ListCell<NotificationController>() {
            @Override
            protected void updateItem(NotificationController notificationController, boolean empty) {
                super.updateItem(notificationController, empty);
                if (empty || notificationController == null) {
                    setGraphic(null);
                } else {
                    setGraphic(notificationController.getLayout());
                }
            }
        });
    }

    public ListView<NotificationController> getNotifications_listView() {
        return notifications_listView;
    }

    public NotificationsListViewController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Notifications/NotificationsListView.fxml"));
            loader.setController(this);
//            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading NotificationsListViewController : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
