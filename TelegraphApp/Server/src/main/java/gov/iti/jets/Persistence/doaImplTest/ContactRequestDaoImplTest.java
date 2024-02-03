package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Persistence.doaImpl.ContactRequestDaoImpl;

public class ContactRequestDaoImplTest {
    ContactRequestDaoImpl object = new ContactRequestDaoImpl();
    private void getRequestsByReceiverTest(){
        System.out.println(object.getRequestsByReceiver("555555555"));
    }

    public static void main(String[] args) {
        ContactRequestDaoImplTest object = new ContactRequestDaoImplTest();
        object.getRequestsByReceiverTest();
    }
}
