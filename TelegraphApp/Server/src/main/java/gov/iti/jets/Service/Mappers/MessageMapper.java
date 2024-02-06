package gov.iti.jets.Service.Mappers;

import DTO.MessageDTO;
import gov.iti.jets.Domain.Message;

public class MessageMapper {

    public static MessageDTO messageToMessageDTO(Message message){
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setMessageId(message.getMessage_id());
        messageDTO.setSenderPhone(message.getSender_phone());
        messageDTO.setConversationId(message.getConversation_id());
        messageDTO.setMessageBody(message.getMessage_body());
        messageDTO.setTimeStamp(message.getTimestamp());

        return messageDTO;
    }
    public static Message messageDTOToMessage(MessageDTO messageDTO){
        Message message = new Message();

        message.setMessage_id(messageDTO.getMessageId());
        message.setSender_phone(messageDTO.getSenderPhone());
        message.setConversation_id(messageDTO.getConversationId());
        message.setMessage_body(messageDTO.getMessageBody());
        message.setTimestamp(messageDTO.getTimeStamp());

        return message;
    }

}
