package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Persistence.doaImpl.ConversationDaoImpl;

public class ConversationDaoImplTest {
    ConversationDaoImpl conversationDaoImpl = new ConversationDaoImpl();

    private void get_group_conversations_by_phone_test(){
        System.out.println(conversationDaoImpl.getGroupConversationsByPhone("123456789"));
    }

    public static void main(String[] args) {
        ConversationDaoImplTest conversationDaoImplTest = new ConversationDaoImplTest();
        conversationDaoImplTest.get_group_conversations_by_phone_test();
    }
}
