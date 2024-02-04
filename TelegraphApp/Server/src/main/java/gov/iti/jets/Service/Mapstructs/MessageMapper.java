package gov.iti.jets.Service.Mapstructs;

import DTO.MessageDTO;
import gov.iti.jets.Domain.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "message_id", target = "messageId")
    @Mapping(source = "sender_phone", target = "senderPhone")
    @Mapping(source = "conversation_id", target = "conversationId")
    @Mapping(source = "message_body", target = "messageBody")
    @Mapping(source = "timestamp", target = "timeStamp")
    MessageDTO messageToMessageDTO(Message message);

    // You can add more mappings as needed

    @Mapping(source = "messageId", target = "message_id")
    @Mapping(source = "senderPhone", target = "sender_phone")
    @Mapping(source = "conversationId", target = "conversation_id")
    @Mapping(source = "messageBody", target = "message_body")
    @Mapping(source = "timeStamp", target = "timestamp")
    Message messageDTOToMessage(MessageDTO messageDTO);
}
