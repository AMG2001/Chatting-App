package gov.iti.jets.Persistence;

import gov.iti.jets.Persistence.rowsetimpl.CustomCacheRowsets;

public class ApplicationServices {
    public static void initApplicationServices() {
        // init global jdbc Object that used to perform all sql Operations .
        LocalDatabaseServices.initConnection();
        // init all tables RowSets Objects :
        CustomCacheRowsets.initializeRowSets();
    }
}
