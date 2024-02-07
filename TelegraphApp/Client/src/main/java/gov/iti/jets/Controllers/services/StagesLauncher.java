package gov.iti.jets.Controllers.services;

import gov.iti.jets.Controllers.LeftSideBar.AddContactPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StagesLauncher {

    public static void LaunchNewStage(Parent layout, String title, int width, int height) {
        Stage stage = new Stage();
        Scene scene = new Scene(layout, width, height);
        stage.setMaxHeight(height);
        stage.setMaxWidth(width + 20);
        stage.setMinHeight(height);
        stage.setMaxWidth(width + 20);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static void launchAddContactPage(String title, int width, int height) {
        Stage stage = new Stage();
        AddContactPane addContactPane = new AddContactPane(stage);
        Scene scene = new Scene(addContactPane.getLayout(), width, height);
        stage.setMaxHeight(height);
        stage.setMaxWidth(width + 20);
        stage.setMinHeight(height);
        stage.setMaxWidth(width + 20);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
