package gov.iti.jets.Controllers.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class AppPages {
    private static Pane homePageView;
    private static Pane updateInfoView;

    public static Pane getHomePageView() {
        if (homePageView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(AppPages.class.getResource("/Dashboard/User_Dashboard.fxml"));
                homePageView = loader.load();
            } catch (Exception e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌❌ Error while loading Home Page View : " + e.getMessage());
            }
        }
        return homePageView;
    }

    public static Pane getUpdateInfoView() {
        if (updateInfoView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(AppPages.class.getResource("/UpdateInfo/UpdateInfoPage.fxml"));
                updateInfoView = loader.load();
            } catch (Exception e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌❌ Error while loading Update Info View : " + e.getMessage());
            }
        }
        return updateInfoView;
    }
}
