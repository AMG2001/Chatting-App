package gov.iti.jets.Controllers.services;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBase;
import javafx.stage.Stage;

public class Navigator {
    // used to show Scene that you want to Move For .
    static Parent loadedFXML;

    public static void gotToScene(ButtonBase btn, String fxmlFilePath) {
        try {
            loadedFXML = FXMLLoader.load(Navigator.class.getResource(fxmlFilePath));
        } catch (Exception e) {
            System.out.println("Error done with fxmlFilePath loader in Navigation Function while navigating to : " + fxmlFilePath);
            System.out.println(e.getMessage());
        }
        Stage window = (Stage) btn.getScene().getWindow();
        window.setScene(new Scene(loadedFXML));
    }
}