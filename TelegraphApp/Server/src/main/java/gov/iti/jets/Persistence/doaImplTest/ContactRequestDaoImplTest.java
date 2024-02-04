package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Persistence.doaImpl.ContactRequestDaoImpl;

public class ContactRequestDaoImplTest {
    ContactRequestDaoImpl object = new ContactRequestDaoImpl();
    private void getRequestsByReceiverTest(){
        System.out.println(object.getRequestsByReceiver("555555555"));
    }
    private void addTest(){
        ContactRequest request = new ContactRequest();
        request.setSenderPhone("555555555");
        request.setReceiverPhone("123456789");
        object.add(request);
    }

    public static void main(String[] args) {
        ContactRequestDaoImplTest object = new ContactRequestDaoImplTest();
        //object.getRequestsByReceiverTest();
        object.addTest();
    }
}
