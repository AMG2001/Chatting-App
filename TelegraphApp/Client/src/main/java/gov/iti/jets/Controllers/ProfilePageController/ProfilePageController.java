package gov.iti.jets.Controllers.ProfilePageController;

import DTO.User.UpdatedUserDTO;
import DTO.User.UserDTO;
import gov.iti.jets.Controllers.RegisterPageController.RegisterPageController;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private UserModel userModel;

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

    }

    @FXML
    void cancel(ActionEvent event) {
        Navigator.navigateToHomePage();
    }

    @FXML
    void saveChanges(ActionEvent event) {
        boolean isPicChanged = true;
        if (img_userImage.getImage() == userModel.getProfilePic()) {
            isPicChanged = false;
        }

        if (tf_email.getText() != userModel.getEmail() || tf_userName.getText() != userModel.getUserName() ||
                tf_country.getText() != userModel.getCountry() || ta_bio.getText() != userModel.getBio() ||
                img_userImage.getImage() != userModel.getProfilePic() || datePicker.getValue() != userModel.getDob()
        )//TODO amgad ... password will be added here
        {
//            userModel = new UserModel(
//                    ClientState.getInstance().getLoggedinUserModel().getUserPhone(),
//                    userModel.getPassword(),
//                    tf_userName.getText(), tf_email.getText(), tf_country.getText(),
//                    ClientState.getInstance().getLoggedinUserModel().getStatus(),
//                    ClientState.getInstance().getLoggedinUserModel().getGender(),
//                    ta_bio.getText(), datePicker.getValue(),
//                    , img_userImage.getImage()
//            );
//
//            ClientState.getInstance().setLoggedinUserProperty(userModel);

//            userModel =  ClientState.getInstance().getLoggedinUserModel();
            tf_email.setText(userModel.getEmail());
            tf_userName.setText(userModel.getUserName());
            tf_country.setText(userModel.getCountry());
            ta_bio.setText(userModel.getBio());
            img_userImage.setImage(userModel.getProfilePic());
            datePicker.setValue(userModel.getDob());
            //TODO amgad ... password will be added here


            UpdatedUserDTO updatedUserDTO = new UpdatedUserDTO(tf_phoneNumber.getText(), tf_userName.getText(),
                    tf_email.getText(), userModel.getPassword(), datePicker.getValue(), tf_country.getText(),
                    tf_gender.getText(), ta_bio.getText(), FileConverter.convert_imageToBytes(img_userImage.getImage()),
                    isPicChanged);
            //check if you will change  userModel.getPassword() with tf_password.getText()
            try {
                //update in data base
                UserService.getInstance().getRemoteService().updateUser(updatedUserDTO);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        }
        // bt2kd an el email mfehosh 7aga et8aert
        //ht check 3la ely b3do
        // law 7aga at8airt ... h3ml el setText ll7aga elly at8airt b kza . get kza
        //bs el awl h8airha f userModel f client state
        //w b3d kda htnady mn el users services function esmha update user ... hna htb3t rmi ll server w a5leh y update
        //htzhr dialog t2olo an el process done successfull
        //a5r 7aga law el user 8air el sora 5leh true
    }

    @FXML
    void openFilesDialog(ActionEvent event) {
        // TODO , implement ti to allow user to change the image .
        // You can find the same code in RegisterPageController .
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an image");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    img_userImage.setImage(new Image(file.toURI().toString()));
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
}
