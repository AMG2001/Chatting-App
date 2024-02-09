package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.MessageDTO;
import DTO.NotificationDTO;
import RemoteInterfaces.RemoteMessageService;
import RemoteInterfaces.callback.RemoteCallbackInterface;
import gov.iti.jets.Domain.Message;
import gov.iti.jets.Domain.enums.NotificationType;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.dao.MessageDao;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;
import gov.iti.jets.Persistence.doaImpl.MessageDoaImpl;
import gov.iti.jets.Service.CallbackHandlers.MessageCallbackHandler;
import gov.iti.jets.Service.CallbackHandlers.NotificationCallbackHandler;
import gov.iti.jets.Service.Utilities.OnlineUserManager;
import gov.iti.jets.Service.Mappers.MessageMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl extends UnicastRemoteObject implements RemoteMessageService {
    public MessageServiceImpl() throws RemoteException {
    }

    @Override
    public void sendMessage(MessageDTO message) throws RemoteException {

        MessageDao messageImpl = new MessageDoaImpl();
        ConversationDao conversationImpl = new ConversationDaoImpl();

        Message domainMessage = MessageMapper.messageDTOToMessage(message);
        //Add message to the conversation in Database
        System.out.println(message.toString());
//        messageImpl.add(domainMessage);
        //TODO handle null
        //Set message ID
        int messageId = messageImpl.createMessage(domainMessage);
        message.setMessageId(messageId);
        //Use callbacks to send message to online users
        List<String> conversationParticipants;

        //Get a list of phone numbers of all conversation participants
        conversationParticipants = conversationImpl.getConversationParticipants(message.getConversationId());
        MessageCallbackHandler handler = new MessageCallbackHandler();
        NotificationCallbackHandler notificationHandler = new NotificationCallbackHandler();
        //Retrieve the callbackinterfaces of all online users who are also in the conversation
        final List<RemoteCallbackInterface> friends =
                OnlineUserManager.getFriendsFromOnlineList(conversationParticipants);

        //Create new notification
        NotificationDTO notification = new NotificationDTO(
                "1", NotificationType.MESSAGE.toString()
                , LocalDateTime.now(), "You received a new Message");

        //Send message & Notification
        handler.sendMessages(message, friends);
        notificationHandler.sendNotification(notification, friends);


    }

    @Override
    public List<MessageDTO> getAllMessagesForConversation(int conversationId) throws RemoteException {

        MessageDao messageDao = new MessageDoaImpl();

        List<Message> messages = messageDao.getMessagesByConversationId(conversationId);
        if (messages.isEmpty()) {
            return null;
        }
        System.out.println(messages);
        List<MessageDTO> messageDTOS = new ArrayList<>();

        for (Message message : messages) {
            messageDTOS.add(MessageMapper.messageToMessageDTO(message));
        }

        System.out.println("messages successfully received");
        return messageDTOS;
    }

}
