package gov.iti.jets.Controllers.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class AppPages {
    private static Pane homePageView, updateInfoView, registerPage, loginPage;

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

    public static Pane getRegisterPageView() {
        if (registerPage == null) {
            try {
                FXMLLoader loader = new FXMLLoader(AppPages.class.getResource("/RegisterPage/RegisterPage.fxml"));
                registerPage = loader.load();
            } catch (Exception e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌❌ Error while loading Register Page View : " + e.getMessage());
            }
        }
        return registerPage;
    }

    public static Pane getLoginPageView() {
        if (loginPage == null) {
            try {
                FXMLLoader loader = new FXMLLoader(AppPages.class.getResource("/LoginPage/LoginPage.fxml"));
                loginPage = loader.load();
            } catch (Exception e) {
                System.out.println("❌❌❌❌❌❌❌❌❌❌❌❌ Error while loading Login Page View : " + e.getMessage());
            }
        }
        return loginPage;
    }
}
