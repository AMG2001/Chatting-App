package gov.iti.jets.dao;

import java.util.List;
import gov.iti.jets.domain.User;

import javax.sql.rowset.JdbcRowSet;

public class UserDao {
    public User login(String phone, String password) {
        // Implementation goes here
        return null;
    }

    public void createUser(User newUser) {
        // Implementation goes here
    }

    public void updateUser(User user) {
        JdbcRowSet rs = RowsetFactory.getJDBCRowset();
        // Implementation goes here
    }

    public List<User> getAllContactsByPhone(String phone) {
        // Implementation goes here
        return null;
    }

    public int getNumberOfUsers() {
        // Implementation goes here
        return 0;
    }

    public int getNumberOfOnlineUsers() {
        // Implementation goes here
        return 0;
    }

    public List<User> getAll() {
        // Implementation goes here
        return null;
    }
}

