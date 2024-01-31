package gov.iti.jets.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class HomePageView {
    Pane layout;
    FXMLLoader loader;
    public HomePageView(){
        try{
            loader = new FXMLLoader(getClass().getResource("/Dashboard/User_Dashboard.fxml"));
            layout = loader.load();
            System.out.println("Home page View Loaded ✅✅");
        }catch (Exception e){
            System.err.println("Error while loading HomePageView : "+ e.getMessage());
        }
    }

    public Pane getLayout(){
        return layout;
    }
}
