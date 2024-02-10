package gov.iti.jets.Service.CallbackHandlers;

import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public class ServerControlCallbackHandler {
    public void serverShutdown(List<RemoteCallbackInterface> allUsers) {
        for (RemoteCallbackInterface user : allUsers) {
            try {
                user.serverShutdown();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void serverRestart(List<RemoteCallbackInterface> allUsers) {
        for (RemoteCallbackInterface user : allUsers) {
            try {
                user.serverStart();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
