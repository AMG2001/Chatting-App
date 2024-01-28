package gov.iti.jets.Controllers.Shared.LeftSideBar;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class LeftSideBar {
    @FXML
    private ImageView homepageOption;
    @FXML
    private ImageView logoutOption;
    @FXML
    private ImageView notificationsOption;
    @FXML
    private ImageView profileOption;
    @FXML
    public void initialize() {
        homepageOption.setOnMouseClicked(event -> {
            System.out.println("Home Page Option Clicked ##");
        });

        logoutOption.setOnMouseClicked(event -> {
            System.out.println("logoutOption Clicked ##");
        });

        notificationsOption.setOnMouseClicked(event -> {
            System.out.println("notificationsOption Clicked ##");
        });

        profileOption.setOnMouseClicked(event -> {
            System.out.println("profileOption Clicked ##");
        });
    }
}
