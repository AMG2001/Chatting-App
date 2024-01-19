package gov.iti.jets;

import gov.iti.jets.domain.User;
import gov.iti.jets.persistence.dao.UserDao;
import gov.iti.jets.persistence.rowsetimpl.UserRowsetImpl;
import java.sql.Date;
import gov.iti.jets.persistence.rowsetimpl.ContactRequestRowsetImpl;
import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;

public class Server {
    public static void main(String[] args) {
        // init global jdbc Object that used to perform all sql Operations .
        RowsetFactory.initJDBCRowset();
        UserDao user = new UserRowsetImpl();
        user.add(new User("12345657765", "hamda", "hamada@hamada.hamda",
                "password", "Hamada", "ONLINE", "MALE", "Bio",
                "imageREf", new java.sql.Date(System.currentTimeMillis())));
    }

}
