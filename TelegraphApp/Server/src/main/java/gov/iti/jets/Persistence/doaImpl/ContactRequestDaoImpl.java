package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Persistence.dao.ContactRequestDao;

import java.util.List;

public class ContactRequestDaoImpl implements ContactRequestDao {
    @Override
    public List<ContactRequest> getRequestsBySender(String phoneNumber) {
        return null;
    }

    //TODO youssef
    @Override
    public List<ContactRequest> getRequestsByReceiver(String phoneNumber) {
        return null;
    }

    //TODO yousef
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
}
