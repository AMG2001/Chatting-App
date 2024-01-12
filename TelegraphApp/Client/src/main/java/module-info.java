module jets.iti.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens jets.iti.client to javafx.fxml;
    exports jets.iti.client;
}