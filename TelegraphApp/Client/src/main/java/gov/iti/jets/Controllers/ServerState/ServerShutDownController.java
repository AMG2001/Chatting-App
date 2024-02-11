package gov.iti.jets.Controllers.ServerState;

import gov.iti.jets.Controllers.services.Navigator;
import gov.iti.jets.Model.ClientState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class ServerShutDownController {
    @FXML
    void logout(ActionEvent event) {
        ClientState.getInstance().logoutUser();
        Navigator.navigateToLogin();
    }

    FXMLLoader loader;
    VBox layout;

    public ServerShutDownController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/ServerState/ServerShutDown.fxml"));
            loader.setController(this);
            layout = loader.load();
            System.out.println("Server Shut Down Pane Loaded ✅✅");
        } catch (Exception e) {
            System.err.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Server Shut Down : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public VBox getLayout() {
        return layout;
    }

    public void setLayout(VBox layout) {
        this.layout = layout;
    }
}
