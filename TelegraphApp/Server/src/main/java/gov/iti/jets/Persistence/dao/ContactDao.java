package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.Contact;

public interface ContactDao extends GenericDatabaseDao<Contact> {
//    int getNumberOfContactsByPhone(String id);
    public boolean checkIfAlreadyContacts (String userPhone , String contactPhone);
}
