package gov.iti.jets.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class RegisterPageView {
    Pane layout;
    FXMLLoader loader;

    public RegisterPageView() {
        try {
            loader = new FXMLLoader(getClass().getResource("/RegisterPage/RegisterPage.fxml"));
            layout = loader.load();
            System.out.println("Register page View Loaded ✅✅");
        } catch (Exception e) {
            System.err.println("Error while loading RegisterPageVeiw : " + e.getMessage());
        }
    }

    public Pane getLayout() {
        return layout;
    }
}
