package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.MessageDTO;
import RemoteInterfaces.RemoteMessageService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.UserDoaImpl;
import gov.iti.jets.Service.MessageHandler;
import gov.iti.jets.Service.OnlineUsers;
import gov.iti.jets.Service.ThreadPoolManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MessageServiceImpl extends UnicastRemoteObject implements RemoteMessageService {
    protected MessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendMessage(MessageDTO message) throws RemoteException {
        List<String> contacts;

        ConversationDaoImpl impl = new ConversationDaoImpl();

        contacts = impl.getConversationParticipants(message.getConversationId());

        MessageHandler handler = new MessageHandler();

        final ConcurrentHashMap<String, RemoteCallbackInterface> friends =
                OnlineUsers.getFriendsFromOnlineList(contacts);

        ThreadPoolManager.submitOperation(
                ()->handler.sendMessages(message,friends)
        );

    }
}
