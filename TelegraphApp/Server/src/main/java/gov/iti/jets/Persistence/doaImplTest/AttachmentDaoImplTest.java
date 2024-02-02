package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Domain.Attachment;
import gov.iti.jets.Persistence.doaImpl.AttachmentDoaImpl;

public class AttachmentDaoImplTest
{
    AttachmentDoaImpl object = new AttachmentDoaImpl();
    private void getAttachmentByMessageIdTest()
    {
        System.out.println(object.getAttachmentByMessageId(1));
    }
    private void addTest(){
        Attachment entity = new Attachment();
        entity.setMessageId(3);
        entity.setAttachmentName("image2.png");
        entity.setAttachmentType("png");
        object.add(entity);
    }
    public static void main(String[] args) {
        AttachmentDaoImplTest object = new AttachmentDaoImplTest();
        //object.getAttachmentByMessageIdTest();
        object.addTest();
    }
}
