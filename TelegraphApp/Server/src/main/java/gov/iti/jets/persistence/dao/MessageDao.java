package gov.iti.jets.persistence.dao;

import gov.iti.jets.domain.Message;

import java.util.List;

public interface MessageDao extends GenericDatabaseDao<Message>{
    List<Message> getMessagesByConversationId(String conversationId);
}
