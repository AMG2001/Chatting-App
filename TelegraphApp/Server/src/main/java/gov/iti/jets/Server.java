package gov.iti.jets;

import gov.iti.jets.domain.User;
import gov.iti.jets.persistence.dao.UserDao;
import gov.iti.jets.persistence.rowsetimpl.UserRowsetImpl;

import java.sql.Date;

public class Server {
    public static void main(String[] args){

        UserDao user = new UserRowsetImpl();
        user.add(new User("12345657765", "hamda", "hamada@hamada.hamda",
                "password", "Hamada", "ONLINE", "MALE", "Bio",
                "imageREf", new java.sql.Date(System.currentTimeMillis())));

    }

}
