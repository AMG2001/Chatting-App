package gov.iti.jets.Domain;

public class Attachment {
    private int attachmentId;
    private int messageId;
    private String attachmentName;
    private String attachmentType;

    //TODO add String FileLocation

    public Attachment() {
    }

    public Attachment(int attachmentId, int messageId, String attachmentName, String attachmentType) {
        this.attachmentId = attachmentId;
        this.messageId = messageId;
        this.attachmentName = attachmentName;
        this.attachmentType = attachmentType;
    }


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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

    @Override
    public String toString() {
        return "Attachment{" +
                "attachmentId=" + attachmentId +
                ", messageId=" + messageId +
                ", attachmentName='" + attachmentName + '\'' +
                ", attachmentType='" + attachmentType + '\'' +
                '}';
    }
}
