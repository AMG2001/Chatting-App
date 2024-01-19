package gov.iti.jets.persistence.dao;

import gov.iti.jets.domain.Attachment;

public interface AttachmentDao extends GenericDatabaseDao<Attachment> {

    Attachment getAttachmentByMessageId(String messageId);
}
