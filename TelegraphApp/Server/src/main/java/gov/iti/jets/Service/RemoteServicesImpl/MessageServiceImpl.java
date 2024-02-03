package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.MessageDTO;
import RemoteInterfaces.RemoteMessageService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.Message;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.dao.MessageDao;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.MessageDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.MessageCallbackHandler;
import gov.iti.jets.Service.OnlineUserManager;
import gov.iti.jets.Service.Mapstructs.MessageMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MessageServiceImpl extends UnicastRemoteObject implements RemoteMessageService {
    public MessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendMessage(MessageDTO message) throws RemoteException{

        MessageDao messageImpl = new MessageDoaImpl();
        ConversationDao conversationImpl = new ConversationDaoImpl();

        Message domainMessage = MessageMapper.INSTANCE.messageDTOToMessage(message);
        //Add message to the conversation in Database
        messageImpl.add(domainMessage);

        //Use callbacks to send message to online users
        List<String> conversationParticipants;

        //Get a list of phone numbers of all conversation participants
        conversationParticipants = conversationImpl.getConversationParticipants(message.getConversationId());
        MessageCallbackHandler handler = new MessageCallbackHandler();
        //Retrieve the callbackinterfaces of all online users who are also in the conversation
        final ConcurrentHashMap<String, RemoteCallbackInterface> friends =
                OnlineUserManager.getFriendsFromOnlineList(conversationParticipants);

        handler.sendMessages(message,friends);

//        ThreadPoolManager.submitOperation(
//                ()->handler.sendMessages(message,friends)
//        );

    }
}
