package gov.iti.jets;

import gov.iti.jets.Controllers.Shared.CustomEnums;
import gov.iti.jets.Controllers.config.AppViews;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gov.iti.jets.Controllers.config.AppPages;

import java.rmi.RemoteException;

public class Client extends Application {
    @Override
    public void start(Stage primaryStage) {
        AppViews.getInstance().initStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        try {
            UserService.getInstance().getRemoteService().updateStatus(ClientState.getInstance().getLoggedinUserModel().getUserPhone(), CustomEnums.UserStatus_OFFLINE);
        } catch (RemoteException e) {
            CustomDialogs.showErrorDialog("Error while updating status to offline when shutting down the app" + e.getMessage());
        }
        System.exit(0);
    }
}