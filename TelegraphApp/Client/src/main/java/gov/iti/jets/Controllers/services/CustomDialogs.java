package gov.iti.jets.Controllers.services;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.*;

public class CustomDialogs {

    static public void showInformativeDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        alert.showAndWait();

    }

    static public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
