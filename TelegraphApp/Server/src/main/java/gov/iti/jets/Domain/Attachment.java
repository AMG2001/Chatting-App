package gov.iti.jets.Domain;

public class Attachment {
    private int attachmentId;
    private String attachmentName;
    private String attachmentType;
    private String attachmentLocation;
    private int conversationId;

    public Attachment() {
    }

    public Attachment(int attachmentId, String attachmentName, String attachmentType, String attachmentLocation, int conversationId) {
        this.attachmentId = attachmentId;
        this.attachmentName = attachmentName;
        this.attachmentType = attachmentType;
        this.attachmentLocation = attachmentLocation;
        this.conversationId = conversationId;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }
}
