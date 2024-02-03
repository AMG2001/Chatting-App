package gov.iti.jets.Controllers.HomePageController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class InitialLayoutController {
    VBox layout;
    FXMLLoader loader;

    // Private static variable of the same class that is the only instance of the class
    private static InitialLayoutController single_instance = null;

    // Private constructor to restrict instantiation of the class from other classes
    private InitialLayoutController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ChatArea/initialLayout.fxml"));
            layout = loader.load();
            loader.setController(this);
            System.out.println("Initial Layout Pane Loaded ✅✅");
        } catch (Exception e) {
            System.err.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Initial Layout : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Public static method to create instance of Singleton class
    public static synchronized InitialLayoutController getInstance() {
        if (single_instance == null)
            single_instance = new InitialLayoutController();
        return single_instance;
    }

    public VBox getLayout() {
        return layout;
    }
}
