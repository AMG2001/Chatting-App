package gov.iti.jets.ServiceContext;

import RemoteInterfaces.RemoteConversationService;
import RemoteInterfaces.RemoteMessageService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class ConversationService {
    private static ConversationService instance;
    private RemoteConversationService service;

    private ConversationService() {
        try {
            Registry registry = RemoteRegistry.getInstance().getRegistry();
            service = (RemoteConversationService) registry.lookup("ConversationService");

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error getting Conversation Service from remote registry: "+e.getMessage());
        }
    }

    public static ConversationService getInstance() {
        if (instance == null) {
            synchronized (ConversationService.class) {
                if (instance == null) {
                    instance = new ConversationService();
                }
            }
        }
        return instance;
    }

    public RemoteConversationService getRemoteService()
    {
        return service;
    }


}
