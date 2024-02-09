package DTO;

import java.io.Serial;
import java.io.Serializable;

public class AttachmentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = Long.MIN_VALUE;
    private int attachmentId;
    private String attachmentType;
    private int conversationId;
    private String attachmentName;
    private byte[] attachment;
    public AttachmentDTO(){

    }
    public AttachmentDTO(int attachmentId, String attachmentType, int conversationId, String attachmentName, byte[] attachment) {
        this.attachmentId = attachmentId;
        this.attachmentType = attachmentType;
        this.conversationId = conversationId;
        this.attachmentName = attachmentName;
        this.attachment = attachment;
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

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
