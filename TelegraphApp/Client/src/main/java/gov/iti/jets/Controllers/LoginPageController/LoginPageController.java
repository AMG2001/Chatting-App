package gov.iti.jets.Controllers.LoginPageController;

import DTO.UserDTO;
import DTO.UserLoginDTO;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FieldsValidator;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.UserModel;
import gov.iti.jets.ServiceContext.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import gov.iti.jets.Controllers.config.AppPages;
import gov.iti.jets.Controllers.services.Navigator;

import java.rmi.RemoteException;

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
        /**
         * Handle User input from UI .
         * Create User Login DTO .
         * pass user login dto and Remote CallBack Interface Obj to login method .
         * ** Note that this RemoteCallBack is because that server need it for other details .
         */
        String phoneNumber = tf_email.getText().trim();
        String password = tf_password.getText().trim();
        if (FieldsValidator.isValidPhoneNumber(phoneNumber) && FieldsValidator.isValidPassword(password)) {
            try {
                UserDTO userDTO = UserService.getInstance().getRemoteService().login(new UserLoginDTO(phoneNumber, password));
                UserModel userModel = new UserModel(userDTO);
                if (userDTO != null) {
                    Navigator.navigateToHomePage();
                }
            } catch (RemoteException e) {
                CustomDialogs.showErrorDialog("Please Enter Valid Phone Number or Password");
                e.printStackTrace();
            }
        }
//        UserLoginDTO user = new UserLoginDTO();
//        UserService.getInstance().getRemoteService().login();
    }

    @FXML
    void navigateToSignUp(ActionEvent event) {
        Navigator.navigateToRegister();
    }

}
/**
 * Login will return null in many cases .
 * 1. Phone Number or password .
 * 2. password is null .
 * So when i need to print "Please Enter Valid Phone Number or Password"
 */