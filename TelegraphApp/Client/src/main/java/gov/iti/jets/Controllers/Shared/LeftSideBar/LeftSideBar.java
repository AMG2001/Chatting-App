package gov.iti.jets.Controllers.Shared.LeftSideBar;

import DTO.LogoutDTO;
import gov.iti.jets.Controllers.HomePageController.HomePageController;
import gov.iti.jets.Controllers.HomePageController.RightPaneManager;
import gov.iti.jets.Controllers.Shared.LeftSideBar.group.CreateGroupPaneController;
import gov.iti.jets.Controllers.Shared.Notifications.NotificationController;
import gov.iti.jets.Controllers.Shared.Notifications.NotificationsListViewController;
import gov.iti.jets.Controllers.services.ChatBot.ChatBotChatPaneViewer;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.CustomPopupMenus;
import gov.iti.jets.Controllers.services.Navigator;
import gov.iti.jets.Controllers.services.StagesLauncher;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.Model.NotificationModel;
import gov.iti.jets.ServiceContext.UserService;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.rmi.RemoteException;

public class LeftSideBar {
    @FXML
    private Button btn_addContact;

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
    private Text userEmail;

    @FXML
    private ImageView userImage;

    @FXML
    private Text userName;
    @FXML
    private Button btn_chatWithAI;
    boolean isErrorOccured = false;
    NotificationsListViewController notificationsListViewController;

    @FXML
    public void initialize() {
        userName.setText(ClientState.getInstance().getLoggedinUserModel().getUserName());
        userEmail.setText(ClientState.getInstance().getLoggedinUserModel().getEmail());
        userImage.setImage(ClientState.getInstance().getLoggedinUserModel().getProfilePic());
        notificationsListViewController = new NotificationsListViewController();
        btn_notifications.setOnAction(event -> {
            new NotificationsPaneViewer().showNotifications();
        });
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
            if (!isErrorOccured) {
                // TODO Clear Client State Observable List and Model .
                Navigator.navigateToRegister();
            }
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
        ReceivedRequestsPaneViewer receivedRequestsPaneViewer = new ReceivedRequestsPaneViewer();
        receivedRequestsPaneViewer.showRequestsPane();
    }

    @FXML
    void addGroup(ActionEvent event) {
        StagesLauncher.LaunchNewStage(new CreateGroupPaneController().layout, "Create New Group", 400, 500);
    }

    @FXML
    void addNewContact(ActionEvent event) {
        AddContactPane addContactPane = new AddContactPane();
        StagesLauncher.LaunchNewStage(addContactPane.getLayout(), "Add New Contact", 500, 300);
    }

    @FXML
    void openChatWithAIPane(ActionEvent event) {
        StagesLauncher.LaunchNewStage(new ChatBotChatPaneViewer().getLayout(), "Chat With AI", 500, 600);
    }
}
