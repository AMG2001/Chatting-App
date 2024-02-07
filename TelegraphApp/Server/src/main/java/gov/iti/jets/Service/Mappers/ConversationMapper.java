package gov.iti.jets.Service.Mappers;

import DTO.ConversationDTO;
import DTO.Group.GroupDTO;
import gov.iti.jets.Domain.Conversation;

public class ConversationMapper {

    public static ConversationDTO conversationToConversationDTO(Conversation conversation){
        ConversationDTO conversationDTO = new ConversationDTO();

        conversationDTO.setConversationId(conversation.getConversationId());
        conversationDTO.setType(conversation.getType());

        return conversationDTO;
    }

    public static GroupDTO conversationToGroupDTO(Conversation conversation){
        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setGroupName(conversation.getConversationName());

        return groupDTO;
    }
}
