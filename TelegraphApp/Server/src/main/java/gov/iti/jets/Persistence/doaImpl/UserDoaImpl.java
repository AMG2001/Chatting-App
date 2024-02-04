package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.Attachment;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.Gender;
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

            pst.setString(1, entity.getPhoneNumber());
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
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        Connection con=null;
        PreparedStatement pst= null;
        ResultSet rs=null;

        try{
            con=DBConnectionPool.DATASOURCE.getConnection();

            String sql ="select * from user;";
            pst=con.prepareStatement(sql);


            rs = pst.executeQuery();

            while (rs.next()){
                String phoneNumber = rs.getString("phone_number");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Date dob = rs.getDate("dob");
                String country = rs.getString("country");
                String gender = rs.getString("gender");
                String bio = rs.getString("bio");
                String status = rs.getString("status");
                String picture = rs.getString("picture");

                User user= new User();
                user.setPhoneNumber(phoneNumber);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setDob(dob.toLocalDate());
                user.setCountry(country);
                user.setGender(Gender.valueOf(gender));
                user.setBio(bio);
                user.setStatus(UserStatus.valueOf(status));
                user.setPicture(picture);

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
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return users;
    }

    @Override
    public void update(User entity) {

        Connection con=null;
        PreparedStatement pst= null;

        try {
            con=DBConnectionPool.DATASOURCE.getConnection();

            String sql ="UPDATE User SET name=?, email=?, password=?, dob=?, country=?, gender=?, bio=? " +
                        "WHERE phone_number=?";
            pst=con.prepareStatement(sql);

            pst.setString(1, entity.getName());
            pst.setString(2, entity.getEmail());
            pst.setString(3, entity.getPassword());
            pst.setDate(4, Date.valueOf(entity.getDob()));
            pst.setString(5, entity.getCountry());
            pst.setString(6, entity.getGender().name());
            pst.setString(7, entity.getBio());
            pst.setString(8, entity.getPhoneNumber());

            pst.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pst != null) pst.close();
                if (con != null) con.close();
                //DBConnectionPool.DATASOURCE.close();
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
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            String sql = "select * from user where phone_number=?;";
            pst = con.prepareStatement(sql);
            pst.setString(1,phone);

            rs = pst.executeQuery();

            while (rs.next()){
                user = new User();
                user.setPhoneNumber(phone);
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getDate("dob").toLocalDate());
                user.setCountry(rs.getString("country"));
                user.setGender(Gender.valueOf(rs.getString("gender")));
                user.setBio(rs.getString("bio"));
                user.setStatus(UserStatus.valueOf(rs.getString("status")));
                user.setPicture(rs.getString("picture"));
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
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return user;
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
                user.setPhoneNumber(contactPhone);
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
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return users;
    }


    @Override
    public int getNumberOfUsers() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            String sql = "select * from user ;";
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()){
                count++;
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
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return count;
    }

    @Override
    public int getNumberOfOnlineUsers() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            String sql = "select * from user where status = 'ONLINE';";
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            while (rs.next()){
                count++;
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
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return count;
    }

    @Override
    public void updateProfilePic(String phone, String profilePic) {
        Connection con=null;
        PreparedStatement pst= null;

        try {
            con=DBConnectionPool.DATASOURCE.getConnection();
            con.setAutoCommit(true);
            String sql ="update User\n" +
                    "set picture = ?\n" +
                    "where phone_number = ?;";
            pst=con.prepareStatement(sql);

            pst.setString(1, profilePic);
            pst.setString(2, phone);


            pst.executeUpdate();
            System.out.println("updated successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pst != null) pst.close();
                if (con != null) con.close();
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void updateStatus(String phone, UserStatus status) {
        Connection con=null;
        PreparedStatement pst= null;

        try {
            con=DBConnectionPool.DATASOURCE.getConnection();
            con.setAutoCommit(true);
            String sql ="update user\n" +
                    "set status = ?\n" +
                    "where phone_number = ?;";
            pst=con.prepareStatement(sql);

            pst.setString(1, String.valueOf(status));
            pst.setString(2, phone);


            pst.executeUpdate();
            System.out.println("updated successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pst != null) pst.close();
                if (con != null) con.close();
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //TODO yousef
    // I give him in user phone number , name , picture and status only ... I want to know if he needs anything else
    //TODO marwan
    //TODO moataz
    @Override
    public List<User> getAllContactsByPhone(String phone) {
        List<User> users = new ArrayList<>();

        Connection con=null;
        PreparedStatement pst= null;
        ResultSet rs=null;

        try{
            con=DBConnectionPool.DATASOURCE.getConnection();

            String sql ="select c.contact_phone As contact_phone, u.name As contact_name, u.status As contact_status, u.picture As contact_picture " +
                    "from Contact c,User u " +
                    "where u.phone_number = c.contact_phone " +
                    "and c.user_phone=? ";

            pst=con.prepareStatement(sql);

            pst.setString(1,phone);


            rs = pst.executeQuery();

            while (rs.next()){
                String contactPhone = rs.getString("contact_phone");
                String contactName = rs.getString("contact_name");
                String contactStatus = rs.getString("contact_status");
                String contactPic= rs.getString("contact_picture");

                User user= new User();
                user.setPhoneNumber(contactPhone);
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
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return users;
    }


}
