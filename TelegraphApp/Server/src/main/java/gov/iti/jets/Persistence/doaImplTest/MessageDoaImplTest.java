package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Persistence.doaImpl.MessageDoaImpl;

public class MessageDoaImplTest {
    MessageDoaImpl messageDoaImpl = new MessageDoaImpl();

    private void getMessagesByConversationIdTest(){
        System.out.println(messageDoaImpl.getMessagesByConversationId(1));
    }

    public static void main(String[] args) {
        MessageDoaImplTest messageDoaImplTest = new MessageDoaImplTest();

        //messageDoaImplTest.getMessagesByConversationIdTest();
    }

}
