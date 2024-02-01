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

import javax.mail.Message;

public class HomePageController {

    @FXML
    private ListView<ContactCardDataModel> lv_offlineContacts;

    @FXML
    private ListView<ContactCardDataModel> lv_onlineContacts;

    private ObservableList<ContactCardDataModel> onlineContactsList = FXCollections.observableArrayList();

    private ObservableList<ContactCardDataModel> offlineContactsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // to make listviews streamed with contacts list ObservableArrayList .
        initListViewsBindings();
        // TODO : load online contacts from database .
        loadOnlineContacts();
        // To change the shape of contact card .
        changeListViewCell();
        // to add action for each item in listview .
        setListViewItemsAction();
        // init leftSide Bar Actions.
        setLeftSideBarActions();
    }


    private void setLeftSideBarActions() {

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
        lv_offlineContacts.setCellFactory(param -> new ListCell<ContactCardDataModel>() {
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
        lv_onlineContacts.itemsProperty().bind(new SimpleListProperty<>(onlineContactsList));
        lv_offlineContacts.itemsProperty().bind(new SimpleListProperty<>(offlineContactsList));
    }

    private void loadOnlineContacts() {
        System.out.println("Data Loaded ##");
        for (int i = 0; i < 10; i++) {
            ContactCardDataModel contactCardObjOnline = new ContactCardDataModel("Amgad" + i, "Bio", new Image("/Dashboard/Images/employees_9552503.png"));
            onlineContactsList.add(contactCardObjOnline);
            ContactCardDataModel contactCardObjOffline = new ContactCardDataModel("Offline Contact " + i, "Bio", new Image("/Dashboard/Images/employees_9552503.png"));
            offlineContactsList.add(contactCardObjOffline);
        }
        System.out.println("Contact added to online contacts ");
    }

    private void setListViewItemsAction() {
        lv_onlineContacts.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                // TODO implement open Contact Chat Functionallity .
                ContactCardDataModel contactCardObj = newVal.getController();
                CustomDialogs.showInformativeDialog("Online contact clicked: " + contactCardObj.getContactName());
            }
        });
    }


}
