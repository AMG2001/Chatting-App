package gov.iti.jets.persistence.rowsetimpl;

import gov.iti.jets.domain.Contact;
import gov.iti.jets.persistence.dao.ContactDao;

import java.util.List;

public class ContactRowsetImpl implements ContactDao {


    @Override
    public int getNumberOfContactsByPhone(String id) {
        return 0;
    }

    @Override
    public void add(Contact entity) {

    }

    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public void update(Contact entity) {

    }

    @Override
    public void delete(Contact entity) {

    }
}
