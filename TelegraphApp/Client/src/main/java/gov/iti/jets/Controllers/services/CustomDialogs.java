package gov.iti.jets.Controllers.services;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import javax.swing.*;
import java.util.Optional;

public class CustomDialogs {

    static public void showInformativeDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    public static Dialog showInformativeDialogWithActions(String message,
                                                          Runnable acceptAction,
                                                          Runnable cancelAction) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(message);
        ButtonType acceptButton = new ButtonType("Accept");
        ButtonType cancelButton = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(cancelButton, acceptButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == acceptButton) {
                acceptAction.run();
            } else if (result.get() == cancelButton) {
                cancelAction.run();
            }
        }
        return alert;
    }


    static public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
