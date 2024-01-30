package gov.iti.jets.ServiceContext;

import RemoteInterfaces.RemoteAttachmentService;
import RemoteInterfaces.RemoteConversationService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class AttachmentService {
    private static AttachmentService instance;
    private RemoteAttachmentService service;

    private AttachmentService() {
        try {
            Registry registry = RemoteRegistry.getInstance().getRegistry();
            service = (RemoteAttachmentService) registry.lookup("AttachmentService");

        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error getting Attachment Service from remote registry: "+e.getMessage());
        }
    }

    public static AttachmentService getInstance() {
        if (instance == null) {
            synchronized (AttachmentService.class) {
                if (instance == null) {
                    instance = new AttachmentService();
                }
            }
        }
        return instance;
    }

    public RemoteAttachmentService getRemoteService()
    {
        return service;
    }

}
