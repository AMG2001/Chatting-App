package gov.iti.jets.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class ProfilePageView {
    FXMLLoader loader;
    Pane layout;

    public ProfilePageView() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Profile/Profile.fxml"));
            layout = loader.load();
            System.out.println("update info View Loaded ✅✅");
        } catch (Exception e) {
            System.err.println("Error while loading UpdateInfoView : " + e.getMessage());
        }
    }

    public Pane getLayout() {
        return layout;
    }
}
