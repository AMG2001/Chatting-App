package gov.iti.jets.persistence.rowsetimpl;

import java.util.List;

import gov.iti.jets.persistence.dao.UserDao;
import gov.iti.jets.domain.User;

public class UserRowsetImpl implements UserDao {


    @Override
    public void add(User entity) {

    }

    @Override
    public User getById(String phone) {
        return null;
    }

    public List<User> getAll() {
        // Implementation goes here
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> getAllContactsByPhone(String phone) {
        return null;
    }

    @Override
    public int getNumberOfUsers() {
        return 0;
    }

    @Override
    public int getNumberOfOnlineUsers() {
        return 0;
    }
}

