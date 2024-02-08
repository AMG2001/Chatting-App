package gov.iti.jets.Controllers.LeftSideBar.group;

import DTO.User.ContactDTO;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.User.ContactModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class CreateGroupPaneController {

    @FXML
    private MFXButton btn_done;

    @FXML
    private Text groupSelectedContacts;

    @FXML
    private ListView<ContactModel> lv_allContactNames;
    FXMLLoader loader;
    public VBox layout;

    public CreateGroupPaneController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Shared/LeftSideBar/Group/AddGroupPane.fxml"));
            loader.setController(this);
            layout = loader.load();
            bindListViewOnClientContactsList();
            createListViewCellFactory();
        } catch (IOException e) {
            System.out.println("can not load sendRequestPane" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void bindListViewOnClientContactsList() {
        lv_allContactNames.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().contactsList));
    }

    private void createListViewCellFactory() {
        lv_allContactNames.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ContactModel contactModel, boolean empty) {
                super.updateItem(contactModel, empty);
                if (empty || contactModel == null) {
                    setGraphic(null);
                } else {
                    Text text = new Text(contactModel.getName());
                    setGraphic(text);
                }
            }
        });
    }
}
