package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Domain.enums.RequestStatus;
import gov.iti.jets.Persistence.doaImpl.ContactRequestDaoImpl;

import java.time.LocalDateTime;

public class ContactRequestDoaImplTest {
    ContactRequestDaoImpl contactRequestDao = new ContactRequestDaoImpl();

    private void updateTest(){
        ContactRequest request = new ContactRequest();
        request.setSenderPhone("123456789");
        request.setReceiverPhone("555555555");
        request.setRequestStatus(RequestStatus.ACCEPTED);
        request.setResponseDate(LocalDateTime.now());

        contactRequestDao.update(request);
    }
    private void checkIfRequestExistTest(){
        ContactRequest request = new ContactRequest();
        request.setSenderPhone("123456789");
        request.setReceiverPhone("555555555");

        System.out.println(contactRequestDao.checkIfRequestExist(request));
    }
    public static void main(String[] args) {
        ContactRequestDoaImplTest contactRequestDoaImplTest = new ContactRequestDoaImplTest();
        contactRequestDoaImplTest.checkIfRequestExistTest();
    }
}
