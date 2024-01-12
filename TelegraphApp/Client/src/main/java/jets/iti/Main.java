package jets.iti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jets.iti.config.AppPages;

public class Main extends Application {

    FXMLLoader fxmlLoader;
    Scene scene;

    @Override
    public void init() throws Exception {
        fxmlLoader = new FXMLLoader(Main.class.getResource(AppPages.getLoginPage()));
        System.out.println("Login page loaded #");
        scene = new Scene(fxmlLoader.load());
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // singleton class .
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}