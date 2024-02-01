package gov.iti.jets.Controllers.services;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class CustomPopupMenus {
    public static ContextMenu getNotificationsMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem();
        Pane customPane = new Pane(); // Add your custom Pane here
        customPane.setPrefSize(600, 600);
        // Add elements to your custom Pane
        customPane.getChildren().add(new Label("All Notifications"));
        menuItem.setGraphic(customPane);
        contextMenu.getItems().add(menuItem);
        return contextMenu;
    }
}
