package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.ServiceContext.MessageService;
import gov.iti.jets.ServiceContext.RequestService;
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
        right_pane.getChildren().add(InitialLayoutController.getInstance().getLayout());
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
            ContactCardDataModel contactCardObjOnline = new ContactCardDataModel("Amgad" + i, "Bio", new Image("/Dashboard/Images/employees_9552503.png"));
            contactsList.add(contactCardObjOnline);
        }
    }

    private void setListViewItemsAction() {
        lv_onlineContacts.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                ContactCardDataModel contactCardObj = newVal.getController();
                right_pane.getChildren().clear();
                System.out.println("Right Pane Cleared");
                ChatPaneController chatPaneController = new ChatPaneController();
                // TODO Add Contact Card Model Obj to Chat Pane setController method 👇🏻👇🏻 .
                chatPaneController.setControllerValues(contactCardObj);
                right_pane.getChildren().add(chatPaneController.getLayout());
                System.out.println("Right Pane Added");
            }
        });
    }


}