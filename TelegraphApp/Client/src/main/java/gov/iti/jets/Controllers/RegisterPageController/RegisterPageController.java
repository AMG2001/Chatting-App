package gov.iti.jets.Controllers.RegisterPageController;

import gov.iti.jets.services.CustomDialogs;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import gov.iti.jets.config.AppPages;
import gov.iti.jets.services.Navigator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.util.regex.Pattern;

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

    private boolean isGenderChoosen = false;

    @FXML
    public void pickUserImage(MouseEvent event) {
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
        /**
         * Signup function work as the following :
         * 1- Check if the entered Number is 11 number or not , check if all entered are digits or not. ✅
         * 2- check if the length of password is 8 or higher . ✅
         * 3. Check if the name entered or not . ✅
         * 4- if email is entered correctly or not . ✅
         * 5- Check if the Country Entered or not .
         */
        boolean isValid = true;
        // ************************* Phone Number Checking *****************************
        if (tf_phone.getLength() == 11) {
            char[] phoneInCharArray = tf_phone.getText().toCharArray();
            for (char digit : phoneInCharArray) {
                if (!Character.isDigit(digit)) {
                    CustomDialogs.showErrorDialog("This is Not valid Phone Number !!");
                    isValid = false;
                    break;
                }
            }
            // ************************* Password Checking *****************************
            if (isValid && tf_password.getText().length() >= 8) {
                // ************************* Name Checking *****************************
                if (tf_name.getText().length() > 3) {
                    // ************************* Email Format Checking *****************************
                    if (isValidEmail(tf_email.getText())) {
                        // ************************* Country Format Checking *****************************
                        if (tf_country.getText().length() > 4) {
                            // ************************* Check if the gender choosen or not *************************
                            if (gender.getSelectedToggle() != null) {
                                // TODO implement Registering Functionality .
                                System.out.println(gender.getSelectedToggle().selectedProperty().getName());
                            } else {
                                CustomDialogs.showErrorDialog("Gender is not choosen !!");
                            }
                        } else if (tf_country.getText().length() == 0) {
                            isValid = false;
                            CustomDialogs.showErrorDialog("You can't leave Country Field Empty !!");
                        } else {
                            isValid = false;
                            CustomDialogs.showErrorDialog("This is not valid Country Name");
                        }
                    } else {
                        isValid = false;
                        CustomDialogs.showErrorDialog("This is not valid Email !!");
                    }
                } else if (tf_name.getText().length() == 0) {
                    isValid = false;
                    CustomDialogs.showErrorDialog("You can't leave Name Field Empty !!");
                } else {
                    isValid = false;
                    CustomDialogs.showErrorDialog("This is not valid Name");
                }

            } else {
                isValid = false;
                CustomDialogs.showErrorDialog("Password must be 8 Character or Higher !!");
            }
        } else {
            isValid = false;
            CustomDialogs.showErrorDialog("This is Not valid Phone Number !!");
        }
    }

    @FXML
    public void gotoLogin(ActionEvent actionEvent) {
        Navigator.gotToScene(link_gotoLogin, AppPages.getLoginPage());
    }

    /**
     * ********************************  Internal Operational Function **************************
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }


}