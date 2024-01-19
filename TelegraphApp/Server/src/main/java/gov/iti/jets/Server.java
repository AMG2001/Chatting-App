package gov.iti.jets;

import gov.iti.jets.persistence.rowsetimpl.ContactRequestRowsetImpl;
import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;

public class Server {
    public static void main(String[] args) {
        // init global jdbc Object that used to perform all sql Operations .
        RowsetFactory.initJDBCRowset();

    }

}
