package gov.iti.jets.Controllers.LoginPageController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import gov.iti.jets.Controllers.config.AppPages;
import gov.iti.jets.Controllers.services.Navigator;

public class LoginPageController {

    @FXML
    private Hyperlink link_signup;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Button btn_login;

    @FXML
    private TextField tf_email;

    @FXML
    void loginUser(ActionEvent event) {
        System.out.println("Login Button Clicked");
    }

    @FXML
    void navigateToSignUp(ActionEvent event) {
        Navigator.gotToScene(link_signup, AppPages.getRegisterPage());
    }

}
