package gov.iti.jets.Controllers.services;

import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

public class CustomLoadingIndicators {
    // Create a new dialog
    private static Dialog<Void> dialog = new Dialog<>();

    public static void showLoadingIndicator() {
        dialog.setTitle("Loading...");
        // Create a new progress indicator
        ProgressIndicator progressIndicator = new ProgressIndicator();
        // Add the progress indicator to the dialog
        VBox vbox = new VBox(progressIndicator);
        dialog.getDialogPane().setContent(vbox);
        // Set the close request handler
        dialog.setOnCloseRequest(event -> dialog.close());
        // Show the dialog
        Platform.runLater(dialog::show);
    }

    public static void closeLoadingIndicator() {
        dialog.close();
    }
}
