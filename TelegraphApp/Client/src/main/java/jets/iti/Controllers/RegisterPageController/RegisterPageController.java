package jets.iti.Controllers.RegisterPageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import jets.iti.config.AppPages;
import jets.iti.services.Navigator;

public class RegisterPageController {
    @FXML
    Hyperlink link_goToLogin;
    @FXML
    Button btn_register;

    @FXML
    public void gotoLogin(ActionEvent actionEvent) {
        Navigator.gotToScene(link_goToLogin, AppPages.getLoginPage());
    }
}
