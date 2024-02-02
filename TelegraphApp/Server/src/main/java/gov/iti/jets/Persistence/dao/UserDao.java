package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.UserStatus;

import java.util.List;

public interface UserDao extends GenericDatabaseDao<User> ,RetrievableByID<User,String>{

     List<User> getContactsByPhoneAndStatus(String phone, UserStatus status);
     int getNumberOfUsers();
     int getNumberOfOnlineUsers();
     void updateStatus(String phone , UserStatus status);
}
