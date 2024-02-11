package gov.iti.jets.Controllers.LoginPageController;

import DTO.User.UserDTO;
import DTO.User.UserLoginDTO;
import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.Emails.EmailsService;
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
import java.util.concurrent.CompletableFuture;

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
        String phoneNumber = tf_email.getText().trim();
        String password = tf_password.getText().trim();
        if (FieldsValidator.isValidPhoneNumber(phoneNumber) && FieldsValidator.isValidPassword(password)) {
            CompletableFuture.supplyAsync(() -> {
                        try {
                            ServerCallback serverCallBack = new ServerCallback();
                            System.out.println("Phone Number : " + phoneNumber + " Password : " + password);
                            UserDTO userDTO = UserService.getInstance().getRemoteService().login(new UserLoginDTO(phoneNumber, password), serverCallBack);
                            if (userDTO != null) {
                                UserModel userModel = new UserModel(userDTO);
                                ClientState.getInstance().setLoggedinUserProperty(userModel);
                                ClientState.getInstance().loadAllUserData();
                                return userDTO;
                            } else {
                                throw new Exception("User DTO is Null");
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .thenAcceptAsync(userDTO -> {
                        // This block runs on the JavaFX Application Thread after the previous stage completes
                        tf_password.clear();
                        try {
                            UserService.getInstance().getRemoteService().updateStatus(ClientState.getInstance().getLoggedinUserModel().getUserPhone(), CustomEnums.UserStatus_ONLINE);
                            EmailsService emailsService = new EmailsService();
                            emailsService.sendEmail(ClientState.getInstance().getLoggedinUserModel().getEmail(), "Telegraph App Logging", "You have successfully logged in , Nice to see you again");
                            Platform.runLater(() -> Navigator.navigateToHomePage());
                        } catch (RemoteException e) {
                            CustomDialogs.showErrorDialog("Error while updating status to offline while logging out !!");
                        }
                    }, Platform::runLater)
                    .exceptionally(ex -> {
                        // This block runs if an exception occurs in any of the previous stages
                        CustomDialogs.showErrorDialog("Please Enter Valid Phone Number or Password");
                        ex.printStackTrace();
                        return null;
                    });
        }
    }

    @FXML
    void navigateToSignUp(ActionEvent event) {
        Navigator.navigateToRegister();
    }
}