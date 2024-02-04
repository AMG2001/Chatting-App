package DTO;

import java.io.Serializable;
import java.util.List;

public class GroupDTO implements Serializable {

    private List<GroupMemberDTO> groupMembers;
    private ConversationDTO conversation;

    public GroupDTO(List<GroupMemberDTO> groupMembers, ConversationDTO conversation) {
        this.groupMembers = groupMembers;
        this.conversation = conversation;
    }

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
        this.conversation = conversation;
    }


    public List<GroupMemberDTO> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMemberDTO> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
