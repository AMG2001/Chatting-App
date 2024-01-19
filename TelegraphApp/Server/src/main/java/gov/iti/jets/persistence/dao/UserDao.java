package gov.iti.jets.persistence.dao;

import gov.iti.jets.domain.User;

import java.util.List;

public interface UserDao extends GenericDatabaseDao<User> ,RetrievableByID<User,String>{

     List<User> getAllContactsByPhone(String phone);
     int getNumberOfUsers();
     int getNumberOfOnlineUsers();
}
