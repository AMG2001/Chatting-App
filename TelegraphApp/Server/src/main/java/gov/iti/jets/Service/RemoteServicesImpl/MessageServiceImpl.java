package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.MessageDTO;
import RemoteInterfaces.RemoteMessageService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.Message;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.MessageDoaImpl;
import gov.iti.jets.Service.MessageHandler;
import gov.iti.jets.Service.OnlineUsers;
import gov.iti.jets.Service.mappers.MessageMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MessageServiceImpl extends UnicastRemoteObject implements RemoteMessageService {
    public MessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendMessage(MessageDTO message) throws RemoteException{
        MessageDoaImpl messageImpl = new MessageDoaImpl();
        ConversationDaoImpl conversationImpl = new ConversationDaoImpl();
        Message domainMessage = MessageMapper.INSTANCE.messageDTOToMessage(message);
        messageImpl.add(domainMessage);
        List<String> contacts;
        contacts = conversationImpl.getConversationParticipants(message.getConversationId());
        MessageHandler handler = new MessageHandler();
        final ConcurrentHashMap<String, RemoteCallbackInterface> friends =
                OnlineUsers .getFriendsFromOnlineList(contacts);
        handler.sendMessages(message,friends);

//        ThreadPoolManager.submitOperation(
//                ()->handler.sendMessages(message,friends)
//        );

    }
}
