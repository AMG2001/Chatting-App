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
        stage.setMinHeight(620);
        stage.setMinWidth(800);
        stage.setWidth(800);
        stage.setHeight(620);
        stage.setMaxWidth(800);
        stage.setMaxHeight(620);
        stage.setTitle("Telegraph - Login");
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
        stage.setMinHeight(780);
        stage.setMinWidth(1200);
        stage.setWidth(1200);
        stage.setHeight(780);
        stage.setMaxWidth(1200);
        stage.setMaxHeight(770);
    }

    public void showUpdateInfoPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getUpdateInfoView());
        stage.setTitle("Telegraph - Update Info");
        stage.setMinHeight(800);
        stage.setMinWidth(780);
        stage.setWidth(800);
        stage.setHeight(780);
        stage.setMaxWidth(800);
        stage.setMaxHeight(780);
    }

    public void showRegisterPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getRegisterPageView());
        stage.setTitle("Telegraph - Register");
        stage.setMinHeight(800);
        stage.setMinWidth(1100);
        stage.setWidth(1100);
        stage.setHeight(800);
        stage.setMaxWidth(1100);
        stage.setMaxHeight(800);
    }

    public void showServerShutdownPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getServerShutdownPage());
        stage.setTitle("Server Out");
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setMaxWidth(600);
        stage.setMaxHeight(400);
    }

    public void showLoginPage() {
        Scene currentScene = stage.getScene();
        currentScene.setRoot(AppPages.getLoginPageView());
        stage.setMinHeight(630);
        stage.setMinWidth(800);
        stage.setWidth(800);
        stage.setHeight(630);
        stage.setTitle("Telegraph - Login");
    }

}
