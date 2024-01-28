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

    private ObservableList<ContactCardDataModel> offlineContacts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        initListViewsBindings();
        System.out.println("List view binding");
        loadOnlineContacts();
        System.out.println("Online contacts loaded");
        changeListViewCell();
    }

    private void changeListViewCell() {
        lv_onlineContacts.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ContactCardDataModel contactCardDataModel, boolean empty) {
                super.updateItem(contactCardDataModel, empty);
                if (empty || contactCardDataModel == null) {
                    setGraphic(null);
                } else {
                    ContactCardDataModel contactCardObj = new ContactCardDataModel().setComponentAttribute("Amgad", "Amgad Contact Bio", new Image("/assets/images/add_image.png"));
                    setGraphic(contactCardObj.getLoader());
                }
            }
        });
        System.out.println("Onlie listview cell changed");
    }


    private void initListViewsBindings() {
        lv_onlineContacts.itemsProperty().bind(new SimpleListProperty<>(onlineContactsList));
    }

    private void loadOnlineContacts() {
        ContactCardDataModel contactCardObj = new ContactCardDataModel();
        contactCardObj.setComponentAttribute("Amgad", "Bio", new Image("/assets/images/add_image.png"));
        onlineContactsList.add(contactCardObj);
        System.out.println("Contact added to online contacts ");
    }
}
