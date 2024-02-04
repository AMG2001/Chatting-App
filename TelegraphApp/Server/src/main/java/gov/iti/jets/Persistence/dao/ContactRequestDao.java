package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.ContactRequest;

import java.util.List;

public interface ContactRequestDao extends GenericDatabaseDao<ContactRequest> {

    List<ContactRequest> getRequestsBySender(String phoneNumber);
    List<ContactRequest> getRequestsByReceiver(String phoneNumber);
    Boolean checkIfRequestExist(ContactRequest request);

}

