package gov.iti.jets.AdminPanel;

import gov.iti.jets.AdminPanel.Controllers.DashboardController;
import javafx.application.Platform;

public class ProcessLog {

    private static DashboardController controller;

    public static void setController(DashboardController yourController) {
        controller = yourController;
    }

    public static void appendToProcessLog(String process) {
        Platform.runLater(() -> {
            if (controller != null) {
                controller.appendToProcessLog(process);
            }
        });
    }

}
