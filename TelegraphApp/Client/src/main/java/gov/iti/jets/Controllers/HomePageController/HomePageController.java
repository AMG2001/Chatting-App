package gov.iti.jets.Controllers.HomePageController;

import gov.iti.jets.Controllers.Shared.ContactCard.ContactCardDataModel;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class HomePageController {
    @FXML
    private ListView<ContactCardDataModel> lv_offlineContacts;

    @FXML
    private ListView<ContactCardDataModel> lv_onlineContacts;

    private ObservableList<ContactCardDataModel> onlineContactsList = FXCollections.observableArrayList();

    private ObservableList<ContactCardDataModel> offlineContactsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initListViewsBindings();
        loadOnlineContacts();
        changeListViewCell();
    }

    private void changeListViewCell() {
        lv_onlineContacts.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ContactCardDataModel contactCardDataModel, boolean empty) {
                super.updateItem(contactCardDataModel, empty);
                if (empty || contactCardDataModel == null) {
//                    setGraphic(null);
                } else {
                    setGraphic(contactCardDataModel.getLoader());
                    System.out.println("online listview graphic changed ..");
                }
            }
        });
        lv_offlineContacts.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ContactCardDataModel contactCardDataModel, boolean empty) {
                super.updateItem(contactCardDataModel, empty);
                if (empty || contactCardDataModel == null) {
//                    setGraphic(null);
                } else {
                    setGraphic(contactCardDataModel.getLoader());
                    System.out.println("online listview graphic changed ..");
                }
            }
        });
        System.out.println("Onlie listview cell changed");
    }


    private void initListViewsBindings() {
        lv_onlineContacts.itemsProperty().bind(new SimpleListProperty<>(onlineContactsList));
        lv_offlineContacts.itemsProperty().bind(new SimpleListProperty<>(offlineContactsList));
    }

    private void loadOnlineContacts() {
        for (int i = 0; i < 10; i++) {
            ContactCardDataModel contactCardObjOnline = new ContactCardDataModel();
            contactCardObjOnline.setComponentAttribute("Amgad" + i, "Bio", new Image("/Dashboard/Images/employees_9552503.png"));
            onlineContactsList.add(contactCardObjOnline);

            ContactCardDataModel contactCardObjOffline = new ContactCardDataModel();
            contactCardObjOffline.setComponentAttribute("Amgad" + i, "Bio", new Image("/Dashboard/Images/employees_9552503.png"));
            offlineContactsList.add(contactCardObjOffline);
        }
        System.out.println("Contact added to online contacts ");
    }
}
