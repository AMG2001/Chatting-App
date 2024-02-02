package gov.iti.jets.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class LoginPageView {
    Pane layout;
    FXMLLoader loader;

    public LoginPageView() {
        try {
            loader = new FXMLLoader(getClass().getResource("/LoginPage/LoginPage.fxml"));
            layout = loader.load();
            System.out.println("Login page View Loaded ✅✅");
        } catch (Exception e) {
            System.err.println("Error while loading LoginPageView : " + e.getMessage());
        }
    }

    public Pane getLayout() {
        return layout;
    }
}
