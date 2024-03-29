package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;

public class ConversationDaoImplTest {
    ConversationDaoImpl conversationDaoImpl = new ConversationDaoImpl();

    private void get_group_conversations_by_phone_test(){
        System.out.println(conversationDaoImpl.getGroupConversationsByPhone("123456789"));
    }

    private void getIndividualConversationIdTest(){
        System.out.println(conversationDaoImpl.getIndividualConversationId("123456789","987654321"));
    }
    private void getAllConversationsByPhoneTest(){
        System.out.println(conversationDaoImpl.getAllConversationsByPhone("555555555"));
    }
    private void getByIdTest(){
        System.out.println(conversationDaoImpl.getById(3));
    }
    private void updateTest()
    {
        Conversation conv = new Conversation();
        conv.setConversationName("moatazCon");
        conv.setConversationImage("image.png");
        conv.setConversationId(3);
        conversationDaoImpl.update(conv);
    }

    private void createGroupConversationTest() {
        Conversation group = new Conversation();
        group.setConversationName("testGroupjhkjhkjhkjhkjhhjhkhjhk");
        System.out.println(conversationDaoImpl.createGroupConversation("123456789", group));
    }

    private void getConversationParticipantsTest(){
        System.out.println(conversationDaoImpl.getConversationParticipants(2));
    }

    private void getGroupMembersByConversationIdTest(){
        System.out.println(conversationDaoImpl.getGroupMembersByConversationId(1));
    }
    public static void main(String[] args) {
        ConversationDaoImplTest conversationDaoImplTest = new ConversationDaoImplTest();
        //conversationDaoImplTest.get_group_conversations_by_phone_test();
        //conversationDaoImplTest.getIndividualConversationIdTest();

        //conversationDaoImplTest.getAllConversationsByPhoneTest();

        //conversationDaoImplTest.getGroupMembersByConversationIdTest();
        conversationDaoImplTest.createGroupConversationTest();

    }
}
