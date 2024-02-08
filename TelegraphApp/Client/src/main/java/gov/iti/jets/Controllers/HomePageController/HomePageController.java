package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Model.ClientState;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class HomePageController {
    @FXML
    private ListView<ConversationCard> lv_onlineContacts;
    @FXML
    private Pane right_pane;

    @FXML
    public void initialize() {
        initListViewsBindings();
        changeListViewCell();
        setListViewItemsAction();
        changeRightPane(InitialLayoutController.getInstance().getLayout());
    }

    public void changeRightPane(Pane newLayout) {
        Platform.runLater(() -> {
            right_pane.getChildren().clear();
            right_pane.getChildren().add(newLayout);
        });
    }

    private void changeListViewCell() {
        lv_onlineContacts.setCellFactory(param -> new ListCell<ConversationCard>() {
            @Override
            protected void updateItem(ConversationCard contactCardDataModel, boolean empty) {
                super.updateItem(contactCardDataModel, empty);
                if (empty || contactCardDataModel == null) {
                    setGraphic(null);
                } else {
                    setGraphic(contactCardDataModel.getLayout());
                }
            }
        });
    }

    private void initListViewsBindings() {
        lv_onlineContacts.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().conversationsList));
    }

    private void setListViewItemsAction() {
        lv_onlineContacts.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                right_pane.getChildren().clear();
                ChatPaneController chatPaneController = new ChatPaneController(newVal);
                right_pane.getChildren().add(chatPaneController.getLayout());
//                ClientState.getInstance().openChat(newVal.getPhoneNumber());
            }
        });
    }


}
