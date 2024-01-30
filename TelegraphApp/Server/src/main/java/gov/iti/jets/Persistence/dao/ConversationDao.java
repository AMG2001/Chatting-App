package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.Conversation;

import java.util.List;

public interface ConversationDao extends GenericDatabaseDao<Conversation>,RetrievableByID<Conversation,String> {
    List<Conversation> getConversationsByPhone(String phone);
}
