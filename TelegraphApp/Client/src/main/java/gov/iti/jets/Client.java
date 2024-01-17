package gov.iti.jets;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gov.iti.jets.config.AppPages;

public class Client extends Application {
    FXMLLoader fxmlLoader;
    Scene scene;

    @Override
    public void init() throws Exception {
        fxmlLoader = new FXMLLoader(getClass().getResource(AppPages.getLoginPage()));
        System.out.println("Login page loaded #");
        scene = new Scene(fxmlLoader.load());
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // singleton class .
        primaryStage.setScene(scene);
        primaryStage.setTitle("Telegraph - Client");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}