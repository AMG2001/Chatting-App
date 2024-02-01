package gov.iti.jets.Controllers.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppViews {
    private static AppViews instance;
    private Stage stage;
    private AppViews() {

    }

    public void initStage(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(AppPages.getHomePageView());
        stage.setScene(scene);
        stage.setTitle("Telegraph - Home Page");
        stage.show();
    }

    public static synchronized AppViews getInstance() {
        if (instance == null) {
            instance = new AppViews();
        }
        return instance;
    }

    public void showHomePage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getHomePageView());
        stage.setTitle("Telegraph - Home Page");
    }

    public void showUpdateInfoPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getUpdateInfoView());
        stage.setTitle("Telegraph - Update Info");
    }

    public void showRegisterPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getRegisterPageView());
        stage.setTitle("Telegraph - Register User");
    }

    public void showLoginPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getLoginPageView());
        stage.setTitle("Telegraph - Login");
    }

}
