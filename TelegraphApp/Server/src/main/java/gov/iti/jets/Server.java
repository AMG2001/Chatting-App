package gov.iti.jets;

import gov.iti.jets.domain.User;
import gov.iti.jets.persistence.dao.UserDao;
import gov.iti.jets.persistence.rowsetimpl.userRowset.UserRowsetImpl;
import gov.iti.jets.services.application.ApplicationServices;

public class Server {
    public static void main(String[] args) {
        /**
         * to load and init all application services :
         * database Rowsets .
         * Connections Objects .
         */
        ApplicationServices.initApplicationServices();
//        UserDao user = new UserRowsetImpl();
//        user.add(new User("01096482185", "hamda", "hamada5@hamada.hamda",
//                "password", "Egypt", "ONLINE", "MALE", "Bio",
//                "imageREf", new java.sql.Date(System.currentTimeMillis())));
//
//        user.add(new User("01096482184", "hamda", "hamada3@hamada.hamda",
//                "password", "Egypt", "ONLINE", "MALE", "Bio",
//                "imageREf", new java.sql.Date(System.currentTimeMillis())));

    }

}
