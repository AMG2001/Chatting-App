package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.Message;
import gov.iti.jets.Persistence.dao.MessageDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageDoaImpl implements MessageDao {

    @Override
    public void add(Message entity) {

    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(Message entity) {

    }



    // get group messages or individual conversation messages
    @Override
    public List<Message> getMessagesByConversationId(int conversationId) {
        List<Message> messages = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBConnectionPool.DATASOURCE.getConnection();

            String sql ="select * from Message\n" +
                    "where conversation_id= ?\n" +
                    "order by timestamp asc;";

            pst = con.prepareStatement(sql);

            pst.setInt(1,conversationId);

            rs = pst.executeQuery();

            while (rs.next()){
                int message_id = rs.getInt("message_id");
                int conversation_id=rs.getInt("conversation_id");
                String sender_phone =rs.getString("sender_phone");
                int attachment_id=rs.getInt("attachment_id");
                String message_body=rs.getString("message_body");
                java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");

                Message message = new Message(message_id,sender_phone, conversation_id,message_body,timestamp.toLocalDateTime());

                messages.add(message);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(rs != null)  rs.close();
                if(pst != null) pst.close();
                if(con != null) con.close();
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return messages;
    }


    @Override
    public int createMessage(Message message)
    {
        Connection con = null;
        PreparedStatement pst = null;
        int messageId = 0;

        try{
            con = DBConnectionPool.DATASOURCE.getConnection();

            String sql = "INSERT INTO Message (conversation_id, sender_phone, message_body, timestamp)\n" +
                    "VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1,message.getConversation_id());
            pst.setString(2,message.getSender_phone());
            pst.setString(3,message.getMessage_body());
            java.sql.Timestamp timestamp1 = Timestamp.valueOf(message.getTimestamp());
            pst.setTimestamp(4, timestamp1);

            pst.executeUpdate();


            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    messageId = generatedKeys.getInt(1);
                }
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(pst != null) pst.close();
                if (con != null) con.close();
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return messageId;
    }


}
