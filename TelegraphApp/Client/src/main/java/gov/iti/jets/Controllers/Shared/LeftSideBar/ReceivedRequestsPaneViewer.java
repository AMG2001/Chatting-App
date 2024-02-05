package gov.iti.jets.Controllers.Shared.LeftSideBar;

import gov.iti.jets.Controllers.Shared.Requests.ReceivedRequestsController;
import gov.iti.jets.Controllers.services.StagesLauncher;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.Requests.RequestReceiveModel;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ReceivedRequestsPaneViewer {
    ListView<ReceivedRequestsController> listView;
    public ReceivedRequestsPaneViewer() {
        listView = new ListView<>();
    }

    public void showRequestsPane() {
        ListView<RequestReceiveModel> listView = new ListView<>();
        listView.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().receivedRequestsList));
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(RequestReceiveModel requestReceiveModel, boolean empty) {
                super.updateItem(requestReceiveModel, empty);
                if (empty || requestReceiveModel == null) {
                    setGraphic(null);
                } else {
                    ReceivedRequestsController receivedRequestsController = new ReceivedRequestsController();
                    receivedRequestsController.setRequestReceiveModel(requestReceiveModel);
                    setGraphic(receivedRequestsController.getLayout());
                }
            }
        });
        StagesLauncher.LaunchNewStage(listView, ClientState.getInstance().receivedRequestsList.size() == 0 ? "There is No Requests" : "All Requests", 600, 500);
    }

}
