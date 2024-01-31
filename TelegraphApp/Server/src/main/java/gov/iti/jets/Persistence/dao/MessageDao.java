package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.Message;

import java.util.List;

public interface MessageDao extends GenericDatabaseDao<Message>{
    List<Message> getMessagesByConversationId(String conversationId);
}
