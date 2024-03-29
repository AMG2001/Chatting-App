package gov.iti.jets.Controllers.services;


import gov.iti.jets.Controllers.config.AppViews;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.stage.Stage;

public class Navigator {
    public static void navigateToHomePage() {
        AppViews.getInstance().showHomePage();
    }

    public static void navigateToUpdateInfo() {
        AppViews.getInstance().showUpdateInfoPage();
    }

    public static void navigateToRegister() {
        AppViews.getInstance().showRegisterPage();
    }

    public static void navigateToLogin() {
        AppViews.getInstance().showLoginPage();
    }

    public static void navigateToServerShutdown() {
        AppViews.getInstance().showServerShutdownPage();
    }
}
