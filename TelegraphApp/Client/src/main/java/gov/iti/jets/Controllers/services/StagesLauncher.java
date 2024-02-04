package gov.iti.jets.Controllers.services;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StagesLauncher {

    public static void LaunchNewStage(Parent layout, String title, int width, int height) {
        Stage stage = new Stage();
        Scene scene = new Scene(layout, width, height);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
