package gov.iti.jets.Controllers.RegisterPageController;

import DTO.UserDTO;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Controllers.services.FieldsValidator;
import gov.iti.jets.Controllers.services.Navigator;
import gov.iti.jets.ServiceContext.UserService;
import io.github.palexdev.materialfx.controls.MFXButton;
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
    private DatePicker datePicker;

    private boolean isGenderChoosen = false;

    @FXML
    private void pickUserImage(MouseEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an image");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    img_user.setImage(new Image(file.toURI().toString()));
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
        /*
        validate all input fields .
         */
        String name = tf_name.getText();
        String email = tf_email.getText();
        String password = tf_password.getText();
        String phoneNumber = tf_phone.getText();
        String country = tf_country.getText();
        String bio = ta_bio.getText();
        String gender = "";
        if (rb_male.isSelected()) {
            gender = "male";
        } else if (rb_female.isSelected()) {
            gender = "female";
        }
        byte[] imageBytes = FileConverter.convert_imageToBytes(img_user.getImage());
        if (FieldsValidator.isValidEmail(email) && FieldsValidator.isValidPassword(password) && FieldsValidator.isValidPhoneNumber(phoneNumber) && FieldsValidator.isValidName(name) && FieldsValidator.isValidCountry(country) && imageBytes != null) {
            try {
                UserDTO userDTO = new UserDTO(phoneNumber, name, email, password, datePicker.getValue(), country, gender, bio, "active", imageBytes);
                UserService.getInstance().getRemoteService().registerUser(userDTO);
                Navigator.navigateToHomePage();
            } catch (RemoteException e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌ Error while Registering user ." + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //    private Date getDate() {
//        String dobStr = ta_dob.getText();
//        SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
//        Date dob = format.parse(dobStr);
//        java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
//        return dobSqlDate;
//    }

    @FXML
    public void gotoLogin(ActionEvent actionEvent) {
        Navigator.navigateToLogin();
    }


}
