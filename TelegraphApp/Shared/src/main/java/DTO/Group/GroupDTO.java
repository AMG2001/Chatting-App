package DTO.Group;

import DTO.ConversationDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupDTO implements Serializable {

    private String groupName;
    private List<GroupMemberDTO> groupMembers;
    private ConversationDTO conversation;
    private byte[] groupImage;



    public GroupDTO() {
        groupMembers=new ArrayList<>();
    }

    public GroupDTO(String conversationName, List<GroupMemberDTO> groupMembers, ConversationDTO conversation, byte[] conversationImage) {
        this.groupName = conversationName;
        this.groupMembers = groupMembers;
        this.conversation = conversation;
        this.groupImage = conversationImage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<GroupMemberDTO> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMemberDTO> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
        this.conversation = conversation;
    }

    public byte[] getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(byte[] groupImage) {
        this.groupImage = groupImage;
    }
}
