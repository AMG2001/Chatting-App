package gov.iti.jets.Controllers.RegisterPageController;

import DTO.User.UserDTO;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Controllers.services.FieldsValidator;
import gov.iti.jets.Controllers.services.Navigator;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.User.UserModel;
import gov.iti.jets.ServiceContext.UserService;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.rmi.RemoteException;

import java.io.File;

public class RegisterPageController {
    @FXML
    private MFXButton btn_register;
    @FXML
    private ComboBox<String> countryBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ImageView img_user;
    @FXML
    private Hyperlink link_gotoLogin;
    @FXML
    private RadioButton rb_female;
    @FXML
    private RadioButton rb_male;
    @FXML
    private TextArea ta_bio;
    @FXML
    private TextField tf_confimPassword;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_name;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_phone;
    private boolean isImagePicked = false;
    boolean isNotRegistered = false;
    ObservableList<String> countriesArray = FXCollections.observableArrayList("Egypt", "Palestine", "Iraq", "Iran", "Syria", "Morocco", "Turkey", "Libya", "Lebanon", "Jordan");
    private String name, email, password, confirmPassword, phoneNumber, country, bio, genderValue = "";

    @FXML
    public void initialize() {
        datePicker.setEditable(false);
        countryBox.setItems(countriesArray);
        countryBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void pickUserImage(MouseEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an image");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    img_user.setImage(new Image(file.toURI().toString()));
                    isImagePicked = true;
                } catch (RuntimeException re) {
                    re.printStackTrace();
                }
            } else {
                throw new RuntimeException("No image selected.");
            }
        } catch (Exception e) {
            System.out.println("Image Not Taken !!");
        }
    }

    @FXML
    void signupUser(ActionEvent event) {

        getAllValuesFromFields();
        if (rb_male.isSelected()) {
            genderValue = "MALE";
        } else if (rb_female.isSelected()) {
            genderValue = "FEMALE";
        }
        /*
        validate all input fields .
         */
        if (FieldsValidator.isValidPhoneNumber(phoneNumber) && FieldsValidator.isValidEmail(email) && FieldsValidator.isValidPassword(password) && FieldsValidator.isValidName(name) && FieldsValidator.isValidCountry(country) && FieldsValidator.isValidPasswordConfirmation(password, confirmPassword) && isImagePicked == true && genderValue != "" && datePicker.getValue() != null) {
            byte[] imageBytes = FileConverter.convert_imageToBytes(img_user.getImage());
            try {
                UserDTO userDTO = new UserDTO(phoneNumber, name, email, password, datePicker.getValue(), country, genderValue, bio, "ONLINE", imageBytes);
                isNotRegistered = UserService.getInstance().getRemoteService().registerUser(userDTO);
                UserModel userModel = new UserModel(userDTO);
                ClientState.getInstance().setLoggedinUserProperty(userModel);
                if (!isNotRegistered) CustomDialogs.showErrorDialog("User Already Exists !!");
            } catch (RemoteException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌ Error while Registering user ." + e.getMessage());
                e.printStackTrace();
            } finally {
                if (isNotRegistered) Navigator.navigateToHomePage();
            }
        } else if (genderValue.isEmpty()) {
            CustomDialogs.showErrorDialog("Please Select Your Gender");
        } else if (isImagePicked == false) {
            CustomDialogs.showErrorDialog("Please Select Your Profile Image");
        } else if (datePicker.getValue() == null) {
            CustomDialogs.showErrorDialog("Please Select Your Date Of Birth");
        }
    }

    @FXML
    public void gotoLogin(ActionEvent actionEvent) {
        Navigator.navigateToLogin();
    }

    private void getAllValuesFromFields() {
        name = tf_name.getText();
        email = tf_email.getText();
        password = tf_password.getText();
        confirmPassword = tf_confimPassword.getText();
        phoneNumber = tf_phone.getText();
        country = countriesArray.get(countryBox.getSelectionModel().getSelectedIndex());
        bio = ta_bio.getText();
        genderValue = "";
    }

}
