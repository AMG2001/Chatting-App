package gov.iti.jets.Controllers.RegisterPageController;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import gov.iti.jets.config.AppPages;
import gov.iti.jets.services.Navigator;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.ImageView;

public class RegisterPageController {

    @FXML
    private Hyperlink link_gotoLogin;

    @FXML
    private MFXButton btn_register;

    @FXML
    private ToggleGroup gender;

    @FXML
    private ImageView img_user;

    @FXML
    private RadioButton rb_female;

    @FXML
    private RadioButton rb_male;

    @FXML
    private TextArea ta_bio;

    @FXML
    private TextField tf_country;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_name;

    @FXML
    private PasswordField tf_password;

    @FXML
    private TextField tf_phone;

    @FXML
    public void pickUserImage(MouseEvent event) {

    }

    @FXML
    public void gotoLogin(ActionEvent actionEvent) {
        Navigator.gotToScene(link_gotoLogin, AppPages.getLoginPage());
    }
}
