package gov.iti.jets.Controllers.ProfilePageController;

import DTO.User.UpdatedUserDTO;
import DTO.User.UserDTO;
import gov.iti.jets.Controllers.RegisterPageController.RegisterPageController;
import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.Emails.EmailsService;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Controllers.services.Navigator;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.UserService;
import gov.iti.jets.Model.User.UserModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.rmi.RemoteException;

public class ProfilePageController {

    @FXML
    private MFXButton btn_cancel;

    @FXML
    private Hyperlink btn_changeUserImage;

    @FXML
    private MFXButton btn_saveChanges;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView img_userImage;

    @FXML
    private TextArea ta_bio;

    @FXML
    private TextField tf_country;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_gender;

    @FXML
    private TextField tf_phoneNumber;

    @FXML
    private TextField tf_userName;
    @FXML
    private ComboBox<String> statusMenuButton;
    private UserModel userModel;
    private boolean isUserImageChanged = false, isUserNameChanged = false;

    @FXML
    public void initialize() {
        userModel = ClientState.getInstance().getLoggedinUserModel();
        tf_email.setText(userModel.getEmail());
        tf_phoneNumber.setText(userModel.getUserPhone());
        tf_userName.setText(userModel.getUserName());
        tf_country.setText(userModel.getCountry());
        tf_gender.setText(userModel.getGender());
        ta_bio.setText(userModel.getBio());
        datePicker.setValue(userModel.getDob());
        img_userImage.setImage(userModel.getProfilePic());
        tf_gender.setEditable(false);
        tf_gender.setDisable(true);
        datePicker.setEditable(false);
        tf_phoneNumber.setEditable(false);
        tf_phoneNumber.setDisable(true);
        initStatusMenuButton();
    }

    private void initStatusMenuButton() {
        String[] allUserStatus = {CustomEnums.UserStatus_ONLINE, CustomEnums.UserStatus_AWAY, CustomEnums.UserStatus_OFFLINE, CustomEnums.UserStatus_BUSY};
        statusMenuButton.setStyle("-fx-text-fill: white;"); // Change the color to red
        statusMenuButton.setValue(allUserStatus[0]);
        statusMenuButton.getItems().addAll(allUserStatus);
        statusMenuButton.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                UserService.getInstance().getRemoteService().updateStatus(ClientState.getInstance().getLoggedinUserModel().getUserPhone(), newValue);
            } catch (RemoteException e) {
                CustomDialogs.showErrorDialog("Error while Changing Status !! / " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    @FXML
    void cancel(ActionEvent event) {
        Navigator.navigateToHomePage();
    }

    @FXML
    void saveChanges(ActionEvent event) {
        if (!tf_userName.getText().trim().equals(userModel.getUserName())) {
            isUserNameChanged = true;
        }
        System.out.println("Save Changes Button Clicked");
        if (isUserImageChanged) {
            String newName = tf_userName.getText().trim();
            Image newImage = img_userImage.getImage();
            UpdatedUserDTO updatedUserDTO = new UpdatedUserDTO(userModel.getUserPhone(), newName, userModel.getEmail(), userModel.getPassword(), userModel.getDob(), userModel.getCountry(), userModel.getGender(), userModel.getBio(), FileConverter.convert_imageToBytes(newImage), true);
            try {
                UserService.getInstance().getRemoteService().updateUser(updatedUserDTO);
                // String userPhone, String password, String userName, String email, String country, String status, String gender, String bio, LocalDate dob, Image profilePic
                UserModel newUserModel = new UserModel(userModel.getUserPhone(), userModel.getPassword(), newName, tf_email.getText().trim(), userModel.getCountry(), userModel.getStatus(), userModel.getGender(), ta_bio.getText().trim(), datePicker.getValue(), newImage);
                ClientState.getInstance().setLoggedinUserProperty(newUserModel);
                CustomDialogs.showInformativeDialog("User Data Updated Successfully !!");
                isUserImageChanged = false;
                isUserNameChanged = false;
            } catch (RemoteException e) {
                CustomDialogs.showErrorDialog("Error while updating user data in Server !!" + e.getMessage());
            }
        } else if (isUserNameChanged) {
            String newName = tf_userName.getText().trim();
            Image newImage = img_userImage.getImage();
            UpdatedUserDTO updatedUserDTO = new UpdatedUserDTO(userModel.getUserPhone(), newName, userModel.getEmail(), userModel.getPassword(), userModel.getDob(), userModel.getCountry(), userModel.getGender(), userModel.getBio(), FileConverter.convert_imageToBytes(newImage), false);
            try {
                UserService.getInstance().getRemoteService().updateUser(updatedUserDTO);
                // String userPhone, String password, String userName, String email, String country, String status, String gender, String bio, LocalDate dob, Image profilePic
                UserModel newUserModel = new UserModel(userModel.getUserPhone(), userModel.getPassword(), newName, tf_email.getText().trim(), userModel.getCountry(), userModel.getStatus(), userModel.getGender(), ta_bio.getText().trim(), datePicker.getValue(), newImage);
                ClientState.getInstance().setLoggedinUserProperty(newUserModel);
                CustomDialogs.showInformativeDialog("User Data Updated Successfully !!");
                isUserNameChanged = false;
            } catch (RemoteException e) {
                CustomDialogs.showErrorDialog("Error while updating user data in Server !!" + e.getMessage());
            }
        }
        Navigator.navigateToHomePage();
    }

    @FXML
    void openFilesDialog(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an image");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    img_userImage.setImage(new Image(file.toURI().toString()));
                    isUserImageChanged = true;
                } catch (RuntimeException re) {
                    isUserImageChanged = false;
                    re.printStackTrace();
                }
            } else {
                throw new RuntimeException("No image selected.");
            }
        } catch (Exception e) {
            System.out.println("Image Not Taken !!");
        }
    }
}
