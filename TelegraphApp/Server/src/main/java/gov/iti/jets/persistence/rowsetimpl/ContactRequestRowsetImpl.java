package gov.iti.jets.persistence.rowsetimpl;

import gov.iti.jets.persistence.dao.ContactRequestDao;
import gov.iti.jets.domain.ContactRequest;

import java.util.List;

public class ContactRequestRowsetImpl implements ContactRequestDao {

    @Override
    public void add(ContactRequest entity) {

    }

    @Override
    public List<ContactRequest> getAll() {
        return null;
    }

    @Override
    public void update(ContactRequest entity) {

    }

    @Override
    public void delete(ContactRequest entity) {

    }

    @Override
    public List<ContactRequest> getRequestsBySender(String phoneNumber) {
        return null;
    }

    @Override
    public List<ContactRequest> getRequestsByReceiver(String phoneNumber) {
        return null;
    }
}
