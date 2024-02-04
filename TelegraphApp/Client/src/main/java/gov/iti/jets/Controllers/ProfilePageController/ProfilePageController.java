package gov.iti.jets.Controllers.ProfilePageController;

import gov.iti.jets.Controllers.services.Emails.EmailsService;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.UserModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    public void initialize() {
        UserModel userModel = ClientState.getInstance().getLoggedinUserModel();
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
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    @FXML
    void openFilesDialog(ActionEvent event) {

    }
}
