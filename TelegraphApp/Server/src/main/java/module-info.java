module jets.iti.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens jets.iti.server to javafx.fxml;
    exports jets.iti.server;
}