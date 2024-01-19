package gov.iti.jets.persistence.dao;

import java.util.List;

public interface GenericDatabaseDao<T> {
    void add(T entity);
    List<T> getAll();
    void update(T entity);
    void delete(T entity);
}
