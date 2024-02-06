package gov.iti.jets.AdminPanel;

import gov.iti.jets.AdminPanel.Controllers.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPanel extends Application {
    public AdminPanel() {

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Parent root = loader.load();
            DashboardController controller = new DashboardController();
            primaryStage.setTitle("Admin Dashboard");
            Scene mainscene = new Scene(root);
            primaryStage.setScene(mainscene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
