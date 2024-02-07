package gov.iti.jets.Service.Mapstructs;

import DTO.*;
import DTO.Group.GroupDTO;
import gov.iti.jets.Domain.Conversation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConversationMapper {
    ConversationMapper INSTANCE = Mappers.getMapper(ConversationMapper.class);

    @Mapping(source = "conversationId", target = "conversationId")
    @Mapping(source = "type", target = "type")
    ConversationDTO conversationToConversationDTO(Conversation conversation);

    @Mapping(source = "conversationName", target = "groupName")
    GroupDTO conversationToGroupDTO(Conversation conversation);
}
