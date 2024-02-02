package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.doaImpl.*;

import java.time.LocalDate;

public class UserDoaImplTest {

    private UserDoaImpl userDoaImpl=new UserDoaImpl();

    private void add_user_test(){
        User user = new User();
        user.setPhone_number("12345678910");
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("securepassword");
        user.setDob(LocalDate.of(1990, 1, 1));
        user.setCountry("Egypt");
        user.setGender(Gender.MALE);
        user.setBio("Some bio text");
        user.setStatus(UserStatus.ONLINE);
        user.setPicture("profile_picture.jpg");

        userDoaImpl.add(user);
    }
    private void update_user_test(){
        User user = new User();
        user.setPhone_number("12345678910");
        user.setName("mona");
        user.setEmail("mona@example.com");
        user.setPassword("securepassword");
        user.setDob(LocalDate.of(1999, 1, 1));
        user.setCountry("Egypt");
        user.setGender(Gender.FEMALE);
        user.setBio("Some bio text");
        user.setPicture("profile_picture.jpg");

        userDoaImpl.update(user);
    }

    private void getContactsByPhoneTest(){
        System.out.println(userDoaImpl.getContactsByPhoneAndStatus("555555555",UserStatus.OFFLINE));
    }
    private void getByIdTest()
    {
        System.out.println(userDoaImpl.getById("555555555"));
    }
    private void getNumberOfOnlineUsersTest()
    {
        System.out.println(userDoaImpl.getNumberOfOnlineUsers());
    }

    public static void main(String[] args) {
        UserDoaImplTest userDoaImplTest= new UserDoaImplTest();

        //userDoaImplTest.add_user_test();
        //userDoaImplTest.update_user_test();
       //userDoaImplTest.getContactsByPhoneTest();
       // userDoaImplTest.getByIdTest();
        userDoaImplTest.getNumberOfOnlineUsersTest();
    }
}
