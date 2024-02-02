package gov.iti.jets.Controllers.services;

import gov.iti.jets.Controllers.Shared.Notifications.NotificationController;
import gov.iti.jets.Controllers.Shared.Notifications.NotificationsListViewController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class CustomPopupMenus {
    private static NotificationsListViewController notificationsListView = new NotificationsListViewController();

    public static ContextMenu getNotificationsMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem();
        // Create a BorderPane to hold the ListView
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(notificationsListView.getNotifications_listView());
        // Set the BorderPane as the graphic for the MenuItem
        menuItem.setGraphic(borderPane);
        contextMenu.getItems().add(menuItem);
        return contextMenu;
    }
}
