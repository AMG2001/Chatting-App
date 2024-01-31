package gov.iti.jets;

import gov.iti.jets.Persistence.ApplicationServices;

public class Server {
    public static void main(String[] args) {
        /**
         * to load and init all application services :
         * 1. initialize database Connection .
         * 2. init all rowsets object to be able to perform crud operations on all tables .
         */
        ApplicationServices.initApplicationServices();
    }

}
