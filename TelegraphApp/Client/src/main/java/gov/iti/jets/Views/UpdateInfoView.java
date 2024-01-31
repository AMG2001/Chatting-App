package gov.iti.jets.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class UpdateInfoView {
    FXMLLoader loader;
    Pane layout;

    public UpdateInfoView() {
        try {
            loader = new FXMLLoader(getClass().getResource("/UpdateInfo/UpdateInfoPage.fxml"));
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
