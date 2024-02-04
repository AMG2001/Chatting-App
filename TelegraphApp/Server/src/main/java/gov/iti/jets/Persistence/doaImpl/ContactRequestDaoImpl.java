package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.ContactRequest;

import gov.iti.jets.Domain.enums.RequestStatus;


import gov.iti.jets.Persistence.dao.ContactRequestDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.*;

import java.util.ArrayList;


import java.util.List;

public class ContactRequestDaoImpl implements ContactRequestDao {
    @Override
    public List<ContactRequest> getRequestsBySender(String phoneNumber) {
        return null;

    }

    //TODO youssef
    //I changed the localDateTime to date
    @Override
    public List<ContactRequest> getRequestsByReceiver(String phoneNumber) {
        List<ContactRequest> requests= new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            con = DBConnectionPool.DATASOURCE.getConnection();

            String sql = "select * from contact_request where receiver_phone = ?;";
            pst = con.prepareStatement(sql);

            pst.setString(1,phoneNumber);

            rs = pst.executeQuery();

            while (rs.next()){
                int request_id = rs.getInt("request_id");
                String sender_phone = rs.getString("sender_phone");
                String receiver_phone = rs.getString("receiver_phone");
                String status = rs.getString("status");
//                Timestamp response_date =rs.getTimestamp("response_date");
                Timestamp send_date = rs.getTimestamp("send_date");

                ContactRequest request = new ContactRequest();
                request.setRequestId(request_id);
                request.setSenderPhone(sender_phone);
                request.setReceiverPhone(receiver_phone);
                request.setRequestStatus(RequestStatus.valueOf(status));
//                request.setResponseDate(response_date.toLocalDateTime());
                request.setSendDate(send_date.toLocalDateTime());

                requests.add(request);
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
        return requests;
    }

    @Override
    public Boolean checkIfRequestExist(ContactRequest request) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Boolean isExist=false;

        try{
            con = DBConnectionPool.DATASOURCE.getConnection();

            String sql = "select * from Contact_Request\n" +
                        "where sender_phone= ?\n" +
                        "and receiver_phone= ?;";
            pst = con.prepareStatement(sql);

            pst.setString(1,request.getSenderPhone());
            pst.setString(2,request.getReceiverPhone());

            rs = pst.executeQuery();

            if (rs.next()) isExist= true;

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
        return isExist;
    }


    @Override
    public void add(ContactRequest entity) {
        Connection con = null;
        PreparedStatement pst = null;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            con.setAutoCommit(true);
            String sql = "insert into contact_request (sender_phone,receiver_phone,send_date)\n" +
                    "values (?,?,?);";
            pst = con.prepareStatement(sql);
            pst.setString(1,entity.getSenderPhone());
            pst.setString(2,entity.getReceiverPhone());
            pst.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            System.out.println("Insertion Complete");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(pst != null) pst.close();
                if (con != null) con.close();
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public List<ContactRequest> getAll() {
        return null;
    }


    @Override
    public void update(ContactRequest request) {

        Connection con = null;

        try{
            con= DBConnectionPool.DATASOURCE.getConnection();

            if(request.getRequestStatus().name().equals("DENIED")){
                deleteRequest(con,request);
            }
            else if(request.getRequestStatus().name().equals("ACCEPTED")){

                con.setAutoCommit(false);

                updateContactRequestTable(con,request);
                insertIntoContactTable(con,request);
                int individualConversationId = insertIntoConversationTable(con);
                InsertIntoUserConversationTable(con,request,individualConversationId);

                con.commit();
            }
        }
        catch (SQLException e){
            try {
                if (con != null) con.rollback();
            }
            catch (SQLException rollbackException) {
                System.out.println(rollbackException.getMessage());
                rollbackException.printStackTrace();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
                //DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }

    private void InsertIntoUserConversationTable(Connection con, ContactRequest request, int individualConversationId)  throws SQLException{

        String sql = "INSERT INTO User_Conversation (phone_number, conversation_id, join_date)\n" +
                "VALUES (?, ?, ?),\n" +
                       "(?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1,request.getSenderPhone());
            pst.setInt(2,individualConversationId);
            java.sql.Timestamp timestamp1 = Timestamp.valueOf(request.getResponseDate());
            pst.setTimestamp(3, timestamp1);


            pst.setString(4,request.getReceiverPhone());
            pst.setInt(5,individualConversationId);
            java.sql.Timestamp timestamp2 = Timestamp.valueOf(request.getResponseDate());
            pst.setTimestamp(6, timestamp2);


            pst.executeUpdate();
        }
    }

    private int insertIntoConversationTable(Connection con) throws SQLException{
        int conversationId = 0;

        String sql = "insert into Conversation (type)\n" +
                "values (?);";
        try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1,"INDIVIDUAL");
            pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    conversationId = generatedKeys.getInt(1);
                }
            }
        }
        return conversationId;
    }

    private void insertIntoContactTable(Connection con, ContactRequest request)  throws SQLException{
        String sql="INSERT INTO Contact (user_phone, contact_phone, add_date)\n" +
                "VALUES (?, ?, ?),\n" +
                       "(?, ?, ?);";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1,request.getSenderPhone());
            pst.setString(2,request.getReceiverPhone());
            java.sql.Timestamp timestamp1 = Timestamp.valueOf(request.getResponseDate());
            pst.setTimestamp(3, timestamp1);



            pst.setString(4,request.getReceiverPhone());
            pst.setString(5,request.getSenderPhone());
            java.sql.Timestamp timestamp2 = Timestamp.valueOf(request.getResponseDate());
            pst.setTimestamp(6, timestamp2);


            pst.executeUpdate();
        }
    }

    private void updateContactRequestTable(Connection con, ContactRequest request) throws SQLException {
        String sql = "update Contact_Request\n" +
                     "set status = 'ACCEPTED' , response_date = ?\n" +
                    "where sender_phone= ?\n" +
                    "and receiver_phone= ?;";
        try (PreparedStatement pst = con.prepareStatement(sql)) {

            java.sql.Timestamp timestamp = Timestamp.valueOf(request.getResponseDate());
            pst.setTimestamp(1, timestamp);


            pst.setString(2,request.getSenderPhone());
            pst.setString(3,request.getReceiverPhone());

            pst.executeUpdate();
        }
    }

    private void deleteRequest(Connection con , ContactRequest request) throws SQLException{
        String sql = "delete from Contact_Request\n" +
                    "WHERE sender_phone= ?\n" +
                    "and receiver_phone= ?;";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1,request.getSenderPhone());
            pst.setString(2,request.getReceiverPhone());

            pst.executeUpdate();
        }
    }


    @Override
    public void delete(ContactRequest entity) {

    }
}
