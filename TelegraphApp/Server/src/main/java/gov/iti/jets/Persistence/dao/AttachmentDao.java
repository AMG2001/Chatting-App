package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.Attachment;

public interface AttachmentDao extends GenericDatabaseDao<Attachment> {

    Attachment getAttachmentByConversationId(int ConversationId);
}
