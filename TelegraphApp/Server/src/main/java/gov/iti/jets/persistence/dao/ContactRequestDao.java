package gov.iti.jets.persistence.dao;

import gov.iti.jets.domain.ContactRequest;

import java.util.List;

public interface ContactRequestDao extends GenericDatabaseDao<ContactRequest> {

    List<ContactRequest> getRequestsBySender(String phoneNumber);
    List<ContactRequest> getRequestsByReceiver(String phoneNumber);

}

