package gov.iti.jets;

import gov.iti.jets.Controllers.config.AppViews;
import gov.iti.jets.ServiceContext.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gov.iti.jets.Controllers.config.AppPages;

public class Client extends Application {
    @Override
    public void start(Stage primaryStage) {
        AppViews.getInstance().initStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}