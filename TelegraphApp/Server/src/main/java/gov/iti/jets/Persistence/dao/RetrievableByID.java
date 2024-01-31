package gov.iti.jets.Persistence.dao;

public interface RetrievableByID<T,ID> {
    T getById(ID id);
}
