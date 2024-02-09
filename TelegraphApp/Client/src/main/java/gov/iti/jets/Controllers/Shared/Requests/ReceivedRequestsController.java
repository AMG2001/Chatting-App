package gov.iti.jets.Controllers.Shared.Requests;

import DTO.Request.RequestResponseDTO;
import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import gov.iti.jets.ServiceContext.RequestService;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;

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
        RequestResponseDTO requestResponseDTO = new RequestResponseDTO(requestReceiveModel.getRequestId(), CustomEnums.RequestStatus_ACCEPTED, requestReceiveModel.getSenderPhone(), requestReceiveModel.getReceiverPhone());
        try {
            RequestService.getInstance().getRemoteService().updateRequest(requestResponseDTO);
        } catch (RemoteException e) {
            CustomDialogs.showErrorDialog("Error while Accepting Request" + e.getMessage());
        }
        Platform.runLater(() -> ClientState.getInstance().receivedRequestsList.removeIf(requestReceiveModel1 -> requestReceiveModel1.getRequestId() == requestReceiveModel.getRequestId())
        );
    }

    @FXML
    void onReject(ActionEvent event) {
        try {
            RequestResponseDTO requestResponseDTO = new RequestResponseDTO(requestReceiveModel.getRequestId(), CustomEnums.RequestStatus_DENIED, requestReceiveModel.getSenderPhone(), requestReceiveModel.getReceiverPhone());
            RequestService.getInstance().getRemoteService().updateRequest(requestResponseDTO);
            Platform.runLater(() -> ClientState.getInstance().receivedRequestsList.removeIf(requestReceiveModel1 -> requestReceiveModel1.getRequestId() == requestReceiveModel.getRequestId())
            );
        } catch (RemoteException e) {
            CustomDialogs.showErrorDialog("Error while Rejecting Request" + e.getMessage());
        }
    }
}
