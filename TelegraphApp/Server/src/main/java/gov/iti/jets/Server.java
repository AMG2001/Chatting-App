package gov.iti.jets;

import gov.iti.jets.Persistence.mysql.DBConnectionPool;
import gov.iti.jets.Service.Utilities.ServerRegistryInitializer;

public class Server {
    public static void main(String[] args) {
        DBConnectionPool datasource = DBConnectionPool.DATASOURCE;
        ServerRegistryInitializer registry = new ServerRegistryInitializer();
    }
}
