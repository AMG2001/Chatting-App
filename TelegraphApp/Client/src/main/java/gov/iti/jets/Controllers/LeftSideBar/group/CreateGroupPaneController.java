package gov.iti.jets.Controllers.LeftSideBar.group;

import DTO.Group.GroupCreationDTO;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FileConverter;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.User.ContactModel;
import gov.iti.jets.ServiceContext.GroupService;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

public class CreateGroupPaneController {
    @FXML
    private ImageView img_groupImage;
    @FXML
    private TextField tf_groupName;
    @FXML
    private MFXButton btn_done;
    @FXML
    private Text groupSelectedContacts; // Change Text to Label
    private boolean isImageChanged = false;
    @FXML
    private ListView<ContactModel> lv_allContactNames;
    FXMLLoader loader;
    public VBox layout;
    private String selectedNames;
    private String[] selectedContactsNumbers;

    @FXML
    void selectGroupImage(MouseEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an image");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    img_groupImage.setImage(new Image(file.toURI().toString()));
                    isImageChanged = true;
                } catch (RuntimeException re) {
                    re.printStackTrace();
                }
            } else {
                isImageChanged = false;
                CustomDialogs.showErrorDialog("No image selected.");
            }
        } catch (Exception e) {
            CustomDialogs.showErrorDialog("Image Not Taken !!");
        }
    }

    public CreateGroupPaneController() {
        try {
            loader = new FXMLLoader(getClass().getResource("/Shared/LeftSideBar/Group/AddGroupPane.fxml"));
            loader.setController(this);
            layout = loader.load();
            bindListViewOnClientContactsList();
            createListViewCellFactory();
            setButtonFunctionallity();
        } catch (IOException e) {
            System.out.println("can not load sendRequestPane" + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isGroupNameEntered() {
        return !tf_groupName.getText().equals("");
    }

    private void setButtonFunctionallity() {
        btn_done.setOnAction(event -> {
            if (selectedNames == null) {
                CustomDialogs.showErrorDialog("You need to choose 2 or more contacts");
            } else if (selectedNames.split(",").length == 1) {
                CustomDialogs.showErrorDialog("You need to choose 2 or more contacts");
            } else if (!isGroupNameEntered()) {
                CustomDialogs.showErrorDialog("Please Enter Group Name !!");
            } else if (!isImageChanged) {
                CustomDialogs.showErrorDialog("Please Select Group Image !!");
            } else {
                String[] membersNames = selectedNames.split(",");
                try {
                    for (String number : selectedContactsNumbers) System.out.println("Number : " + number);
                    GroupCreationDTO groupCreationDTO = new GroupCreationDTO(tf_groupName.getText().trim(), List.of(selectedContactsNumbers), ClientState.getInstance().getLoggedinUserModel().getUserPhone(), FileConverter.convert_imageToBytes(img_groupImage.getImage()));
                    GroupService.getInstance().getRemoteService().createGroup(groupCreationDTO);
                    CustomDialogs.showInformativeDialog("Group Created Successfully.");
                } catch (RemoteException e) {
                    CustomDialogs.showErrorDialog("Error while creating group !!");
                }
            }
        });
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
        lv_allContactNames.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // Enable multi-selection
        lv_allContactNames.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedNames = lv_allContactNames.getSelectionModel().getSelectedItems().stream()
                        .map(ContactModel::getName)
                        .collect(Collectors.joining(", "));
                selectedContactsNumbers = lv_allContactNames.getSelectionModel().getSelectedItems().stream()
                        .map(ContactModel::getPhoneNumber)
                        .collect(Collectors.toList()).toArray(new String[0]);

                String selectedNamesMessage = "Group Members : [ " + selectedNames + " ]";
                groupSelectedContacts.setText(selectedNamesMessage); // Add selected names to the label
            }
        });
    }
}
