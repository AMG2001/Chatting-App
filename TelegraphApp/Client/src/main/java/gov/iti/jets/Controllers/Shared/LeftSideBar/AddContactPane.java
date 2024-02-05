package gov.iti.jets.Controllers.Shared.LeftSideBar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddContactPane
{
    FXMLLoader loader;
    @FXML
    private Button btn_addContact;

    @FXML
    private TextField receiverPhoneNumber;
    public AddContactPane()
    {
        try{
            loader = new FXMLLoader(getClass().getResource("/SendRequest/SendRequest.fxml"));
            loader.setController(this);
            loader.load();
        }catch(IOException e)
        {
            System.out.println("can not load sendRequestPane"+e.getMessage());
        }
    }

    @FXML
    void addContact(ActionEvent event) {

    }
}
