package gov.iti.jets.services.application;

import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;
import gov.iti.jets.services.database.LocalDatabaseServices;

public class ApplicationServices {

    public static void initApplicationServices() {
        LocalDatabaseServices.initConnection();
        // init global jdbc Object that used to perform all sql Operations .
        RowsetFactory.initJDBCRowset();
    }
}
