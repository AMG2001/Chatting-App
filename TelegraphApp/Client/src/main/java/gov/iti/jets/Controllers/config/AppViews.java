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
        Scene scene = new Scene(AppPages.getLoginPageView());
        stage.setScene(scene);
        stage.setTitle("Telegraph - Home Page");
        stage.setWidth(800);
        stage.setHeight(620);
        stage.setMaxWidth(800);
        stage.setMaxHeight(620);
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
        stage.setWidth(1100);
        stage.setHeight(770);
        stage.setMaxWidth(1100);
        stage.setMaxHeight(770);
    }

    public void showUpdateInfoPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getUpdateInfoView());
        stage.setTitle("Telegraph - Update Info");
        stage.setWidth(800);
        stage.setHeight(780);
        stage.setMaxWidth(800);
        stage.setMaxHeight(780);
    }

    public void showRegisterPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getRegisterPageView());
        stage.setTitle("Telegraph - Register User");
        stage.setTitle("Telegraph - Home Page");
        stage.setWidth(1100);
        stage.setHeight(800);
        stage.setMaxWidth(1100);
        stage.setMaxHeight(800);
    }

    public void showLoginPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getLoginPageView());
        stage.setWidth(800);
        stage.setHeight(630);
        stage.setTitle("Telegraph - Login");
    }

}
