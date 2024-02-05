package gov.iti.jets.Controllers.Shared.Requests;

import gov.iti.jets.Model.Requests.RequestReceiveModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ReceivedRequestsController {

    @FXML
    private MFXButton btn_accept;

    @FXML
    private MFXButton btn_reject;

    @FXML
    private Text senderName;

    @FXML
    private Text senderNumber;

    @FXML
    private Text sendingDate;
    public FXMLLoader loader;
    public HBox layout;
    RequestReceiveModel requestReceiveModel;

    public ReceivedRequestsController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Requests/ReceivedRequests.fxml"));
            loader.setController(this);
            layout = loader.load();
        } catch (IOException e) {
            System.out.println("can not load sendRequestPane" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setRequestReceiveModel(RequestReceiveModel requestReceiveModel) {
        this.requestReceiveModel = requestReceiveModel;
        senderName.setText(requestReceiveModel.getSenderName());
        senderNumber.setText(requestReceiveModel.getSenderPhone());
        sendingDate.setText(requestReceiveModel.getSendDate().toString());
    }

    public HBox getLayout() {
        return layout;
    }

    @FXML
    void onAccept(ActionEvent event) {
        // TODO Implement Accept Request Functionality .
    }

    @FXML
    void onReject(ActionEvent event) {
        // Todo implement Reject Functionality .
    }
}
