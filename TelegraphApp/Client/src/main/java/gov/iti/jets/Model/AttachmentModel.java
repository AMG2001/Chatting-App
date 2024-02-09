package gov.iti.jets.Model;

import DTO.AttachmentDTO;

public class AttachmentModel {
    private int attachmentId;
    private String attachmentType;
    private int conversationId;
    private String attachmentName;
    private byte[] attachment;

    // Constructor taking AttachmentDTO as a parameter
    public AttachmentModel(AttachmentDTO dto) {
        this.attachmentId = dto.getAttachmentId();
        this.attachmentType = dto.getAttachmentType();
        this.conversationId = dto.getConversationId();
        this.attachmentName = dto.getAttachmentName();
        this.attachment = dto.getAttachment();
    }

    // Getters and Setters remain unchanged
    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
