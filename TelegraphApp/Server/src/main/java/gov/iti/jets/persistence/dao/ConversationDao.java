package gov.iti.jets.persistence.dao;

import gov.iti.jets.domain.Conversation;

import java.util.List;

public interface ConversationDao extends GenericDatabaseDao<Conversation>,RetrievableByID<Conversation,String> {
    List<Conversation> getGroupConversationsByPhone(String phone);
}
