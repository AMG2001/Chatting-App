package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.User;

import java.util.List;

public interface UserDao extends GenericDatabaseDao<User> ,RetrievableByID<User,String>{

     List<User> getOnlineContactsByPhone(String phone);
     List<User> getOfflineContactsByPhone(String phone);
     int getNumberOfUsers();
     int getNumberOfOnlineUsers();
}
