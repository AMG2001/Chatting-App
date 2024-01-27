package SharedImplClasses;

import SharedClasses.UserDataModel;
import SharedInterfaces.RIUser;
import gov.iti.jets.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RUser extends UnicastRemoteObject implements RIUser {

    public RUser() throws RemoteException {

    }

    @Override
    public void registerUser(UserDataModel userModel) {

    }

    @Override
    public UserDataModel login(String phoneNumber, String password) {
        return null;
    }

}
