package gov.iti.jets.Controllers.services;

import javafx.fxml.FXMLLoader;

public class CustomFXMLLoader {

    public static void loadFXML(String path) {
        FXMLLoader loader = new FXMLLoader(CustomFXMLLoader.class.getResource(path));
        loader.getController();
    }

}
