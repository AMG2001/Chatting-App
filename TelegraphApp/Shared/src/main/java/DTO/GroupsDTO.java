package DTO;

import java.io.Serializable;

public class GroupsDTO implements Serializable {
    private String groupName;
    private byte[] groupImage;
    private ConversationDTO conversation;

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
        this.conversation = conversation;
    }

    public GroupsDTO(String groupName, byte[] groupImage, int conversationId, ConversationDTO conversation) {
        this.groupName = groupName;
        this.groupImage = groupImage;

        this.conversation = conversation;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public byte[] getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(byte[] groupImage) {
        this.groupImage = groupImage;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }
}
