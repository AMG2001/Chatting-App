package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Domain.Attachment;
import gov.iti.jets.Persistence.doaImpl.AttachmentDoaImpl;

public class AttachmentDaoImplTest
{
    AttachmentDoaImpl object = new AttachmentDoaImpl();
    private void getAllAttachmentsByConversationIdTest()
    {
        System.out.println(object.getAllAttachmentsByConversationId(1));
    }
    private void getAttachmentByConversationIdAndAttachmentIdTest()
    {
        System.out.println(object.getAttachmentByConversationIdAndAttachmentId(2,2));
    }
    private void addTest(){
        Attachment entity = new Attachment();
        entity.setAttachmentName("image4.png");
        entity.setAttachmentType("png");
        entity.setConversationId(3);
        entity.setAttachmentLocation("C://telegraph/attachment/image4");
        object.add(entity);
    }
    public static void main(String[] args) {
        AttachmentDaoImplTest object = new AttachmentDaoImplTest();
        //object.getAllAttachmentsByConversationIdTest();
        //object.addTest();
        //object.getAttachmentByConversationIdAndAttachmentIdTest();
        object.addTest();
    }
}
