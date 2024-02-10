package gov.iti.jets.ServiceContext;

import RemoteInterfaces.RemoteGroupService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class GroupService {
    private static GroupService instance;
    private RemoteGroupService service;

    private GroupService() {
        try {
            Registry registry = RemoteRegistry.getInstance().getRegistry();
            service = (RemoteGroupService) registry.lookup("GroupService");

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error getting Group Service from remote registry: "+e.getMessage());
        }
    }

    public static GroupService getInstance() {
        if (instance == null) {
            synchronized (GroupService.class) {
                if (instance == null) {
                    instance = new GroupService();
                }
            }
        }
        return instance;
    }

    public RemoteGroupService getRemoteService()
    {
        return service;
    }


}
