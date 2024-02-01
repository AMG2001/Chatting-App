package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.dao.UserDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDoaImpl implements UserDao {
    @Override
    public void add(User entity) {
        Connection con=null;
        PreparedStatement pst= null;

        try {
            con=DBConnectionPool.DATASOURCE.getConnection();

            String sql ="INSERT INTO User (phone_number, name, email, password, dob, country, gender, bio, status, picture)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst=con.prepareStatement(sql);

            pst.setString(1, entity.getPhone_number());
            pst.setString(2, entity.getName());
            pst.setString(3, entity.getEmail());
            pst.setString(4, entity.getPassword());
            pst.setDate(5, java.sql.Date.valueOf(entity.getDob()));
            pst.setString(6, entity.getCountry());
            pst.setString(7, entity.getGender().name());
            pst.setString(8, entity.getBio());
            pst.setString(9, entity.getStatus().name());
            pst.setString(10, entity.getPicture());

            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pst != null) pst.close();
                if (con != null) con.close();
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User entity) {

        Connection con=null;
        PreparedStatement pst= null;

        try {
            con=DBConnectionPool.DATASOURCE.getConnection();

            String sql ="UPDATE User SET name=?, email=?, password=?, dob=?, country=?, gender=?, bio=?, picture=? " +
                        "WHERE phone_number=?";
            pst=con.prepareStatement(sql);

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getEmail());
            pst.setString(3, entity.getPassword());
            pst.setDate(4, Date.valueOf(entity.getDob()));
            pst.setString(5, entity.getCountry());
            pst.setString(6, entity.getGender().name());
            pst.setString(7, entity.getBio());
            pst.setString(8, entity.getPicture());
            pst.setString(9, entity.getPhone_number());

            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pst != null) pst.close();
                if (con != null) con.close();
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User getById(String phone) {
        return null;
    }


    @Override
    public List<User> getContactsByPhoneAndStatus(String phone,UserStatus status) {
        List<User> users = new ArrayList<>();

        Connection con=null;
        PreparedStatement pst= null;
        ResultSet rs=null;

        try{
            con=DBConnectionPool.DATASOURCE.getConnection();

            String sql ="select c.contact_phone As contact_phone, u.name As contact_name, u.status As contact_status, u.picture As contact_picture " +
                        "from Contact c,User u " +
                        "where u.phone_number = c.contact_phone " +
                        "and c.user_phone=? "+
                        "and u.status= ?";
            pst=con.prepareStatement(sql);

            pst.setString(1,phone);
            pst.setString(2,status.name());

            rs = pst.executeQuery();

            while (rs.next()){
                String contactPhone = rs.getString("contact_phone");
                String contactName = rs.getString("contact_name");
                String contactStatus = rs.getString("contact_status");
                String contactPic= rs.getString("contact_picture");

                User user= new User();
                user.setPhone_number(contactPhone);
                user.setName(contactName);
                user.setStatus(UserStatus.valueOf(contactStatus));
                user.setPicture(contactPic);

                users.add(user);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if (con != null) con.close();
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return users;
    }


    @Override
    public int getNumberOfUsers() {
        return 0;
    }

    @Override
    public int getNumberOfOnlineUsers() {
        return 0;
    }

    @Override
    public void updateStatus(User entity) {

    }
}
