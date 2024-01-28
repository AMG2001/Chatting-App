package SharedInterfaces;

import DTO.UserDataModel;

import java.rmi.Remote;

public interface RIUser extends Remote {
    void registerUser(UserDataModel userModel);

    UserDataModel login(String phoneNumber,String password);
}
