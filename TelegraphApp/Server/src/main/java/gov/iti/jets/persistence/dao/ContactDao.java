package gov.iti.jets.persistence.dao;

import gov.iti.jets.domain.Contact;

public interface ContactDao extends GenericDatabaseDao<Contact> {
    int getNumberOfContactsByPhone(String id);
}
