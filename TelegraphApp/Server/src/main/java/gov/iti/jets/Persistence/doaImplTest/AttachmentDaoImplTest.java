package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Persistence.doaImpl.AttachmentDoaImpl;

public class AttachmentDaoImplTest
{
    AttachmentDoaImpl object = new AttachmentDoaImpl();
    private void getAttachmentByMessageIdTest()
    {
        System.out.println(object.getAttachmentByMessageId(1));
    }
    public static void main(String[] args) {
        AttachmentDaoImplTest object = new AttachmentDaoImplTest();
        object.getAttachmentByMessageIdTest();
    }
}
