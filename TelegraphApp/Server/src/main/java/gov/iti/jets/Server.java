package gov.iti.jets;

import gov.iti.jets.AdminPanel.AdminPanel;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;
import gov.iti.jets.Service.Utilities.ServerRegistryInitializer;
import javafx.application.Application;

public class Server {
    public static void main(String[] args) {
        DBConnectionPool datasource = DBConnectionPool.DATASOURCE;
        ServerRegistryInitializer registry = new ServerRegistryInitializer();
        AdminPanel panel = new AdminPanel();
        Application.launch(AdminPanel.class);


    }
}
