package gov.iti.jets.Controllers.Shared.LeftSideBar;

import DTO.Request.RequestSendDTO;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FieldsValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AddContactPane {
    FXMLLoader loader;
    GridPane layout;
    @FXML
    private Button btn_addContact;

    @FXML
    private TextField receiverPhoneNumber;

    public AddContactPane() {
        try {
            loader = new FXMLLoader(getClass().getResource("/SendRequest/SendRequest.fxml"));
            loader.setController(this);
            layout = loader.load();
        } catch (IOException e) {
            System.out.println("can not load sendRequestPane" + e.getMessage());
        }
    }

    @FXML
    void addContact(ActionEvent event) {
        String phoneNumber = receiverPhoneNumber.getText().trim();
        System.out.println("Phone Number : " + phoneNumber);
        if (phoneNumber.isEmpty()) {
            CustomDialogs.showErrorDialog("You can't leave Phone Number Field Empty !!");
        } else if (FieldsValidator.isValidPhoneNumber(phoneNumber)) {
            //TODO send request to server
//            SentRequestDTO sentRequestDTO = new SentRequestDTO();
        }
    }

    public GridPane getLayout() {
        return layout;
    }
}
