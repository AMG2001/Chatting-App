package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.UserStatus;

import java.util.List;

public interface UserDao extends GenericDatabaseDao<User> ,RetrievableByID<User,String>{

     List<User> getContactsByPhoneAndStatus(String phone, UserStatus status);
     int getNumberOfUsers();
     int getNumberOfOnlineUsers();
     void updateStatus(String phone , UserStatus status);
     List<User> getAllContactsByPhone(String phone);
     void updateProfilePic(String phone, String profilePic);
     public void updateUserEmail(String email,String phone);
     public void updateUserCountry(String country,String phone);
     public void updateUserGender(Gender gender, String phone);
     public void updateUserName(String name,String phone);
}
