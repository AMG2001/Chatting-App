package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Domain.User;

import java.util.List;

public interface ConversationDao extends GenericDatabaseDao<Conversation>,RetrievableByID<Conversation,Integer> {
    List<Conversation> getGroupConversationsByPhone(String phone);
    List<String> getConversationParticipants(int conversationId);
    int getIndividualConversationId(String userPhone,String contactPhone);
    int createGroupConversation(String userPhone, Conversation group);
    List<Conversation> getIndividualConversationsByPhone(String phone);
    List<Conversation> getAllConversationsByPhone(String phone);
    List<User> getGroupMembersByConversationId(int conversation_id);
    void updateGroupImage(int groupId,String groupImage);

}
