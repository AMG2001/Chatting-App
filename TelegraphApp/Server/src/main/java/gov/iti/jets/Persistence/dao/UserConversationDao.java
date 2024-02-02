package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.UserConversation;

import java.util.List;

public interface UserConversationDao extends GenericDatabaseDao<UserConversation> {
    void addGroupMembers(int conversationId, List<String> membersPhones);
}
