package gov.iti.jets.Controllers.Shared.LeftSideBar;

import gov.iti.jets.Controllers.services.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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
    private VBox leftSideBar;

    @FXML
    void addGroup(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        Navigator.navigateToRegister();
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
    void showNotifications(ActionEvent event) {

    }
}
