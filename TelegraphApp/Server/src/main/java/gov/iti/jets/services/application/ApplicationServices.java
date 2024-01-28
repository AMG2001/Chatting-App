package gov.iti.jets.services.application;

import gov.iti.jets.persistence.rowsetimpl.CustomCacheRowsets;
import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;
import gov.iti.jets.services.database.LocalDatabaseServices;

public class ApplicationServices {
    public static void initApplicationServices() {
        // init global jdbc Object that used to perform all sql Operations .
        LocalDatabaseServices.initConnection();
        // init all tables RowSets Objects :
        CustomCacheRowsets.initializeRowSets();
    }
}
