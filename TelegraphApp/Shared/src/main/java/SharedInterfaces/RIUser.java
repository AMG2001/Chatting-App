package SharedInterfaces;

import SharedClasses.UserDataModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RIUser extends Remote {
    void registerUser(UserDataModel userModel);

    UserDataModel login(String phoneNumber,String password);
}
