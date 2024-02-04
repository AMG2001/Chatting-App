package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Domain.Message;
import gov.iti.jets.Persistence.doaImpl.MessageDoaImpl;

import java.time.LocalDateTime;

public class MessageDoaImplTest {
    MessageDoaImpl messageDoaImpl = new MessageDoaImpl();

    private void getMessagesByConversationIdTest(){
        System.out.println(messageDoaImpl.getMessagesByConversationId(1));
    }

    private void createMessageTest(){
        Message message = new Message();
        message.setConversation_id(1);
        message.setMessage_body("haahaha");
        message.setSender_phone("555555555");
        message.setTimestamp(LocalDateTime.now());

        System.out.println(messageDoaImpl.createMessage(message));
    }

    public static void main(String[] args) {
        MessageDoaImplTest messageDoaImplTest = new MessageDoaImplTest();

        //messageDoaImplTest.getMessagesByConversationIdTest();
        messageDoaImplTest.createMessageTest();
    }

}
