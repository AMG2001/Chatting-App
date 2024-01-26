package gov.iti.jets.persistence.rowsetimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.dao.UserDao;
import gov.iti.jets.domain.User;
import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;
import gov.iti.jets.persistence.rowsetimpl.userRowset.UserCacheRowset;
import gov.iti.jets.services.database.LocalDatabaseServices;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;

public class UserRowsetImpl implements UserDao {
    private CachedRowSet rowset;

    /**
     * String query = String.format("INSERT INTO User (phone_number, name, email, password, country, status, gender, bio, picture, dob) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
     * entity.getPhoneNumber(),
     * entity.getName(),
     * entity.getEmail(),
     * entity.getPassword(),
     * entity.getCountry(),
     * entity.getUserStatus(),
     * entity.getGender(),
     * entity.getBio(),
     * entity.getImageReference(),
     * entity.getDob());
     *
     * @param entity
     */

    @Override
    public void add(User entity) {
        try {
            rowset = UserCacheRowset.getInstance().getUserCacheRowset();
            RowsetFactory.userCachedRowsetObj.moveToInsertRow();
            rowset.updateString(1, entity.getPhoneNumber());
            rowset.updateString(2, entity.getName());
            rowset.updateString(3, entity.getEmail());
            rowset.updateString(4, entity.getPassword());
            rowset.updateDate(5, entity.getDob());
            rowset.updateString(6, entity.getCountry());
            rowset.updateString(7, entity.getGender());
            rowset.updateString(8, entity.getBio());
            rowset.updateString(9, entity.getUserStatus());
            rowset.updateString(10, entity.getImageReference());
            rowset.insertRow();
            rowset.moveToCurrentRow();
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
            rowset.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public User getById(String phone) {
        return null;
    }

    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        try {
            rowset.beforeFirst();
            while (rowset.next()) {
                User user = new User();
                user.setPhoneNumber(rowset.getString(1));
                user.setName(rowset.getString(2));
                user.setEmail(rowset.getString(3));
                user.setPassword(rowset.getString(4));
                user.setCountry(rowset.getString(5));
                user.setUserStatus(rowset.getString(6));
                user.setGender(rowset.getString(7));
                user.setBio(rowset.getString(8));
                user.setImageReference(rowset.getString(9));
                user.setDob(rowset.getDate(10));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(User entity) {
        try {
            rowset = UserCacheRowset.getInstance().getUserCacheRowset();
            rowset.absolute(rowset.findColumn("phoneNumber"));
                while (rowset.next()) {
                    if (entity.getPhoneNumber().equals(rowset.getString("phoneNumber"))) {
                        rowset.updateString(1, entity.getPhoneNumber());
                        rowset.updateString(2, entity.getName());
                        rowset.updateString(3, entity.getEmail());
                        rowset.updateString(4, entity.getPassword());
                        rowset.updateDate(5, entity.getDob());
                        rowset.updateString(6, entity.getCountry());
                        rowset.updateString(7, entity.getGender());
                        rowset.updateString(8, entity.getBio());
                        rowset.updateString(9, entity.getUserStatus());
                        rowset.updateString(10, entity.getImageReference());
                        rowset.updateRow();
                        break;
                    }
            }
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(User entity) {
        try {
            rowset = UserCacheRowset.getInstance().getUserCacheRowset();
            rowset.absolute(rowset.findColumn("phoneNumber"));
            while (rowset.next()) {
                if (entity.getPhoneNumber().equals(rowset.getString("phoneNumber"))) {
                    rowset.deleteRow();
                    break;
                }
            }
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
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

