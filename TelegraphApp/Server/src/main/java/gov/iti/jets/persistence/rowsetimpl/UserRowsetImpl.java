package gov.iti.jets.persistence.rowsetimpl;

import java.sql.SQLException;
import java.util.List;

import gov.iti.jets.persistence.dao.UserDao;
import gov.iti.jets.domain.User;

import javax.sql.rowset.JdbcRowSet;

public class UserRowsetImpl implements UserDao {

    @Override
    public void add(User entity) {
        String insertStatement = "INSERT INTO User (phone_number, name, email, password, dob, country, gender, bio, picture) VALUES"+
                " (?,?,?,?,?,?,?,?,?)";

        try{
            JdbcRowSet rowset = RowsetFactory.getJDBCRowset();
            rowset.setCommand(insertStatement);

            rowset.setString(1,entity.getPhoneNumber());
            rowset.setString(2,entity.getName());
            rowset.setString(3,entity.getEmail());
            rowset.setString(4,entity.getPassword());
            rowset.setDate(5,entity.getDob());
            rowset.setString(6,entity.getCountry());
            rowset.setString(7,entity.getGender());
            rowset.setString(8,entity.getBio());
            rowset.setString(9,entity.getImageReference());

            rowset.execute();

        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

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

