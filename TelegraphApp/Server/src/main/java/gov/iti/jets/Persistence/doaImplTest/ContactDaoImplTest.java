package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Persistence.doaImpl.AttachmentDoaImpl;
import gov.iti.jets.Persistence.doaImpl.ContactDaoImpl;

public class ContactDaoImplTest {
    ContactDaoImpl object = new ContactDaoImpl();
    private void checkIfAlreadyContactsTest()
    {
        System.out.println(object.checkIfAlreadyContacts("123456789","555555555"));
    }

    public static void main(String[] args) {
        ContactDaoImplTest object = new ContactDaoImplTest();
        object.checkIfAlreadyContactsTest();
    }
}
