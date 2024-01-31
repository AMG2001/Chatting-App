package gov.iti.jets.Controllers.ProfilePageController;

import gov.iti.jets.Controllers.services.Emails.EmailsService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class ProfilePageController {
    @FXML
    private MFXButton btn_cancel;

    @FXML
    private MFXButton btn_saveChanges;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea ta_bio;

    @FXML
    private MFXTextField tf_country;

    @FXML
    private MFXTextField tf_email;

    @FXML
    private MFXTextField tf_gender;

    @FXML
    private MFXTextField tf_phoneNumber;

    @FXML
    private MFXTextField tf_userName;

    @FXML
    void cancel(ActionEvent event) {
        EmailsService emailsService = new EmailsService();
        emailsService.sendEmail("mohamadamgad09@gmail.com", "First Email", "Nice to meet you");
    }

    @FXML
    void saveChanges(ActionEvent event) {

    }
}
