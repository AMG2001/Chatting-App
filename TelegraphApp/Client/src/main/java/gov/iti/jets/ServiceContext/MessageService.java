package gov.iti.jets.ServiceContext;

import RemoteInterfaces.RemoteMessageService;
import RemoteInterfaces.RemoteUserService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class MessageService {
    private static MessageService instance;
    private RemoteMessageService service;

    private MessageService() {
        try {
            Registry registry = RemoteRegistry.getInstance().getRegistry();
            service = (RemoteMessageService) registry.lookup("MessageService");

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error getting User Service from remote registry: "+e.getMessage());
        }
    }

    public static MessageService getInstance() {
        if (instance == null) {
            synchronized (MessageService.class) {
                if (instance == null) {
                    instance = new MessageService();
                }
            }
        }
        return instance;
    }

    public RemoteMessageService getRemoteService()
    {
        return service;
    }


}
