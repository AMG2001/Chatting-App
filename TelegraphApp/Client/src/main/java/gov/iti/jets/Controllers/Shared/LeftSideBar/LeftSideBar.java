package gov.iti.jets.Controllers.Shared.LeftSideBar;

import DTO.LogoutDTO;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.CustomPopupMenus;
import gov.iti.jets.Controllers.services.Navigator;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.rmi.RemoteException;

public class LeftSideBar {
    @FXML
    private Button btn_addGroup;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_notifications;

    @FXML
    private Button btn_profile;

    @FXML
    private Button btn_showRequests;

    @FXML
    private VBox leftSideBar;

    @FXML
    private Text userEmail;

    @FXML
    private ImageView userImage;

    @FXML
    private Text userName;

    private ContextMenu notificationsMenu;

    @FXML
    private Button btn_addContact;

    boolean isErrorOccured = false;

    @FXML
    void showNotifications(MouseEvent event) {
        notificationsMenu.show(btn_notifications, event.getScreenX(), event.getScreenY());
    }


    @FXML
    public void initialize() {
        notificationsMenu = CustomPopupMenus.getNotificationsMenu();
        btn_notifications.setOnMouseClicked(this::showNotifications);
        userName.setText(ClientState.getInstance().getLoggedinUserModel().getUserName());
        userEmail.setText(ClientState.getInstance().getLoggedinUserModel().getEmail());
        userImage.setImage(ClientState.getInstance().getLoggedinUserModel().getProfilePic());
    }

    @FXML
    void addGroup(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        try {
            System.out.println("Stored User in Application : " + ClientState.getInstance().getLoggedinUserModel().getUserName() + " and Phone number : " + ClientState.getInstance().getLoggedinUserModel().getUserPhone());
            LogoutDTO logoutDTO = new LogoutDTO(ClientState.getInstance().getLoggedinUserModel().getUserName(), ClientState.getInstance().getLoggedinUserModel().getUserPhone());
            UserService.getInstance().getRemoteService().logout(logoutDTO);
            isErrorOccured = false;
        } catch (RemoteException e) {
            isErrorOccured = true;
            CustomDialogs.showErrorDialog("Error while Logging out !!");
            e.printStackTrace();
        } finally {
            if (!isErrorOccured) Navigator.navigateToRegister();
        }
    }

    @FXML
    void moveToHomePage(ActionEvent event) {
        Navigator.navigateToHomePage();
    }

    @FXML
    void moveToProfile(ActionEvent event) {
        Navigator.navigateToUpdateInfo();
    }

    @FXML
    void showAllFriendRequests(ActionEvent event) {

    }

    @FXML
    void addNewContact(ActionEvent event) {
        // TODO - Yousef , show Add Contact Pane with text , textArea and Button .
        /**
         * Note that if the server respond with null , then it mean that the user is not exist .
         * use CustomDialog.show.. to show Error Message .
         */
    }
}
