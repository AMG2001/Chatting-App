package gov.iti.jets.persistence.dao;

public interface RetrievableByID<T,ID> {
    T getById(ID id);
}
