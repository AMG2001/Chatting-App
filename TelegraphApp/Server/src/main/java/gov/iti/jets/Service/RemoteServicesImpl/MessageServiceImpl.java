package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.MessageDTO;
import RemoteInterfaces.RemoteMessageService;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.MessageHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MessageServiceImpl extends UnicastRemoteObject implements RemoteMessageService {
    protected MessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendMessage(MessageDTO message) throws RemoteException {
        List<String> contacts;
        ConversationDaoImpl impl = new ConversationDaoImpl();
        contacts =impl.getConversationParticipants(message.getConversationId());
        MessageHandler handler = new MessageHandler();
        //handler.sendMessages();

    }
}
