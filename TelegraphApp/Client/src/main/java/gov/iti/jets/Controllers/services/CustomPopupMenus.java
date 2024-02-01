package gov.iti.jets.Controllers.services;

import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import gov.iti.jets.Controllers.Shared.Notifications.NotificationController;
import gov.iti.jets.Controllers.Shared.Notifications.NotificationsListViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class CustomPopupMenus {
    public static NotificationsListViewController notificationsListView = new NotificationsListViewController();
    public static NotificationController notificationController = new NotificationController("test", "test", "test", "test");

    public static ContextMenu getNotificationsMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem();
        // Add elements to your custom Pane
        menuItem.setGraphic(notificationsListView.getNotifications_listView());
        contextMenu.getItems().add(menuItem);
        return contextMenu;
    }
}
