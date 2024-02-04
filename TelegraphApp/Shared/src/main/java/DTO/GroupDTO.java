package DTO;

import java.io.Serializable;
import java.util.List;

public class GroupDTO implements Serializable {

    private List<ContactDTO> groupMembers;
    private ConversationDTO conversation;

    public GroupDTO(List<ContactDTO> groupMembers, ConversationDTO conversation) {
        this.groupMembers = groupMembers;
        this.conversation = conversation;
    }

    public List<ContactDTO> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<ContactDTO> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
        this.conversation = conversation;
    }


}
