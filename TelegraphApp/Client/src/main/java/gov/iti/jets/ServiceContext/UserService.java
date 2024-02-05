package gov.iti.jets.ServiceContext;

import RemoteInterfaces.RemoteUserService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class UserService {
    private static UserService instance;
    private RemoteUserService service;

    private UserService() {
        try {
            Registry registry = RemoteRegistry.getInstance().getRegistry();
            service = (RemoteUserService) registry.lookup("UserService");
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error getting User Service from remote registry: "+e.getMessage());
        }
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public RemoteUserService getRemoteService()
    {
        return service;
    }



}
