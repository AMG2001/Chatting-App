package gov.iti.jets.Controllers.LoginPageController;

import DTO.User.UserDTO;
import DTO.User.UserLoginDTO;
import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FieldsValidator;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.User.UserModel;
import gov.iti.jets.ServiceContext.UserService;
import gov.iti.jets.ServiceContext.callback.ServerCallback;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    UserDTO userDTO;

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
                ServerCallback serverCallBack = new ServerCallback();
                System.out.println("Phone Number : " + phoneNumber + " Password : " + password);
                userDTO = UserService.getInstance().getRemoteService().login(new UserLoginDTO(phoneNumber, password), serverCallBack);
                if (userDTO != null) {
                    UserModel userModel = new UserModel(userDTO);
                    ClientState.getInstance().setLoggedinUserProperty(userModel);
                    ClientState.getInstance().loadAllUserData();
                } else {
                    System.out.println("User DTO is Null");
                    CustomDialogs.showErrorDialog("Please Enter Valid Phone Number or Password");
                }
            } catch (RemoteException e) {
                CustomDialogs.showErrorDialog("Please Enter Valid Phone Number or Password");
                e.printStackTrace();
            } finally {
                if (userDTO != null) {
                    tf_password.clear();
                    try {
                        UserService.getInstance().getRemoteService().updateStatus(ClientState.getInstance().getLoggedinUserModel().getUserPhone(), CustomEnums.UserStatus_ONLINE);
                        Platform.runLater(() -> Navigator.navigateToHomePage());
                    } catch (RemoteException e) {
                        CustomDialogs.showErrorDialog("Error while updating status to offline while logging out !!");
                    }
                }
            }
        }
    }

    @FXML
    void navigateToSignUp(ActionEvent event) {
        Navigator.navigateToRegister();
    }


}