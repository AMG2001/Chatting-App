package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.MessageService;
import gov.iti.jets.ServiceContext.RequestService;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.mail.Message;

public class HomePageController {
    @FXML
    private ListView<ContactCardDataModel> lv_onlineContacts;
    @FXML
    private Pane right_pane;
    private ObservableList<ContactCardDataModel> contactsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initListViewsBindings();
        loadOnlineContacts();
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
        lv_onlineContacts.setCellFactory(param -> new ListCell<ContactCardDataModel>() {
            @Override
            protected void updateItem(ContactCardDataModel contactCardDataModel, boolean empty) {
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
        lv_onlineContacts.itemsProperty().bind(new SimpleListProperty<>(contactsList));
    }

    private void loadOnlineContacts() {
        for (int i = 0; i < 10; i++) {
            ContactCardDataModel contactCardObjOnline = new ContactCardDataModel("Amgad" + i, "Bio", "0109648218" + i, new Image("/Dashboard/Images/employees_9552503.png"));
            contactsList.add(contactCardObjOnline);
        }
    }

    private void setListViewItemsAction() {
        lv_onlineContacts.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                right_pane.getChildren().clear();
                ChatPaneController chatPaneController = new ChatPaneController();
                chatPaneController.setControllerValues(newVal);
                right_pane.getChildren().add(chatPaneController.getLayout());
//                ClientState.getInstance().openChat(newVal.getPhoneNumber());
            }
        });
    }


}
