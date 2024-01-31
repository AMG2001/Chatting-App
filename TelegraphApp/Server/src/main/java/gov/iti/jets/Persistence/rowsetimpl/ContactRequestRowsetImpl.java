package gov.iti.jets.Persistence.rowsetimpl;

import gov.iti.jets.Persistence.dao.ContactRequestDao;
import gov.iti.jets.Domain.ContactRequest;

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
