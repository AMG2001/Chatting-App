package gov.iti.jets.Controllers.Shared.Notifications;

import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.NotificationModel;
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
    public ListView<NotificationModel> notifications_listView;
    private FXMLLoader loader;

    @FXML
    public void initialize() {

    }

    private void bindListViewOnNotificationsList() {
        notifications_listView.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().notificationsList));
    }

    private void changeNotificationCellFactory() {
        notifications_listView.setCellFactory(param -> new ListCell<NotificationModel>() {
            @Override
            protected void updateItem(NotificationModel notificationModel, boolean empty) {
                super.updateItem(notificationModel, empty);
                if (empty || notificationModel == null) {
                    setGraphic(null);
                } else {
                    NotificationController notificationController = new NotificationController(notificationModel);
                    // Use the layout of the notificationController as the graphic
                    setGraphic(notificationController.getLayout());
                }
            }
        });
    }

    private String getCurrentTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }

    public NotificationsListViewController() {
        if (loader == null) {
            try {
                loader = new FXMLLoader(getClass().getResource("/Notifications/NotificationsListView.fxml"));
                loader.setController(this);
                loader.load();
            } catch (IOException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading NotificationsListViewController : " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("✅✅✅✅✅✅ Notifications List view initialized");
            // Bind listview on Observable ArrayList .
            bindListViewOnNotificationsList();
            // Set the cell factory
            changeNotificationCellFactory();
        }
    }

    public ListView<NotificationModel> getListView() {
        return notifications_listView;
    }
}
