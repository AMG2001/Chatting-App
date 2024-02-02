package gov.iti.jets.Controllers.Shared.LeftSideBar;

import gov.iti.jets.Controllers.services.CustomPopupMenus;
import gov.iti.jets.Controllers.services.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LeftSideBar {
    @FXML
    private Button btn_addGroup;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_notifications;

    @FXML
    private Button btn_profile;

    @FXML
    private Button btn_showRequests;

    @FXML
    private VBox leftSideBar;

    @FXML
    private Text userEmail;

    @FXML
    private ImageView userImage;

    @FXML
    private Text userName;

    private ContextMenu notificationsMenu;

    @FXML
    void showNotifications(MouseEvent event) {
        notificationsMenu.show(btn_notifications, event.getScreenX(), event.getScreenY());
    }


    @FXML
    public void initialize() {
        notificationsMenu = CustomPopupMenus.getNotificationsMenu();
        btn_notifications.setOnMouseClicked(this::showNotifications);
    }


    @FXML
    void addGroup(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        Navigator.navigateToRegister();
        // TODO cast in Server - marwan work .
    }

    @FXML
    void moveToHomePage(ActionEvent event) {
        Navigator.navigateToHomePage();
    }

    @FXML
    void moveToProfile(ActionEvent event) {
        Navigator.navigateToUpdateInfo();
    }

    @FXML
    void showAllFriendRequests(ActionEvent event) {

    }
}
