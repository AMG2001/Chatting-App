package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.Attachment;

import java.util.List;

public interface AttachmentDao extends GenericDatabaseDao<Attachment> {

    Attachment getAttachmentByConversationIdAndAttachmentId(int conversationId,int attachmentId);
    public List<Attachment> getAllAttachmentsByConversationId(int conversationId);
}
