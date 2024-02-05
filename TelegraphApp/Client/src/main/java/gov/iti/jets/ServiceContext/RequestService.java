package gov.iti.jets.ServiceContext;

import RemoteInterfaces.RemoteRequestService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class RequestService {
    private static RequestService instance;
    private RemoteRequestService service;

    private RequestService() {
        try {
            Registry registry = RemoteRegistry.getInstance().getRegistry();
            service = (RemoteRequestService) registry.lookup("RequestService");
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error getting Request Service from remote registry: "+e.getMessage());
        }
    }

    public static RequestService getInstance() {
        if (instance == null) {
            synchronized (RequestService.class) {
                if (instance == null) {
                    instance = new RequestService();
                }
            }
        }
        return instance;
    }

    public RemoteRequestService getRemoteService()
    {
        return service;
    }
}
