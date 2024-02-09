package gov.iti.jets.Controllers.LeftSideBar;

import DTO.Request.RequestSendDTO;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FieldsValidator;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.RequestService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class AddContactPane {
    FXMLLoader loader;
    VBox layout;
    @FXML
    private Button btn_addContact;

    @FXML
    private TextField receiverPhoneNumber;

    private Stage stage;

    public AddContactPane(Stage stage) {
        try {
            loader = new FXMLLoader(getClass().getResource("/SendRequest/SendRequest.fxml"));
            loader.setController(this);
            layout = loader.load();
            this.stage = stage;
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
            RequestSendDTO requestSendDTO = new RequestSendDTO(LocalDateTime.now(), phoneNumber, ClientState.getInstance().getLoggedinUserModel().getUserPhone());
            Platform.runLater(() -> {
                try {
                    RequestService.getInstance().getRemoteService().sendRequest(requestSendDTO);
                } catch (RemoteException e) {
                    CustomDialogs.showErrorDialog("This Phone Number is not exist !!");
                    e.printStackTrace();
                }
            });
            stage.close();
        }
    }

    public VBox getLayout() {
        return layout;
    }
}
