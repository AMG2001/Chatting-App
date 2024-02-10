package gov.iti.jets.Controllers.HomePageController;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class RightPaneManager {
    private static RightPaneManager instance = null;
    private Parent rightPane;

    private RightPaneManager() {

    }

    public static RightPaneManager getInstance() {
        if (instance == null) {
            instance = new RightPaneManager();
        }
        return instance;
    }

    public void setRightPane(Parent pane) {
        this.rightPane = pane;
    }

    public Parent getRightPane() {
        return this.rightPane;
    }
}

