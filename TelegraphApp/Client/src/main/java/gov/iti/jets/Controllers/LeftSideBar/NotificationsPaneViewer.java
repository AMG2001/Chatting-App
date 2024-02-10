package gov.iti.jets.Controllers.LeftSideBar;

import gov.iti.jets.Controllers.Shared.Notifications.NotificationController;
import gov.iti.jets.Controllers.services.StagesLauncher;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.NotificationModel;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class NotificationsPaneViewer {

    public ListView<NotificationModel> createListView() {
        return new ListView<>();
    }

    void showNotifications() {
        ListView<NotificationModel> listView = createListView();
        listView.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().notificationsList));
        listView.setCellFactory(param -> new ListCell<>() {
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
        StagesLauncher.LaunchNewStage(listView, ClientState.getInstance().notificationsList.size() == 0 ? "There is No Notifications" : "All Notification", 470, 500);
    }
}
