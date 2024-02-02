package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.Message;
import gov.iti.jets.Persistence.dao.MessageDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public int getIndividualConversationId(String userPhone,String contactPhone){
        List<Message> messages= new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs=null;
        int conversationId = 0;

        try{
            con = DBConnectionPool.DATASOURCE.getConnection();

            String sql = "select uc.conversation_id As conversation_id\n" +
                    "from User_Conversation uc, Conversation c\n" +
                    "where uc.conversation_id = c.conversation_id\n" +
                    "and c.type = 'INDIVIDUAL'\n" +
                    "and uc.phone_number in (?,?)\n" +
                    "group by uc.conversation_id\n" +
                    "having COUNT(uc.phone_number) = 2;";
            pst = con.prepareStatement(sql);

            pst.setString(1,userPhone);
            pst.setString(2,contactPhone);

            rs = pst.executeQuery();

            while (rs.next()){
                conversationId = rs.getInt("conversation_id");
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (con != null) con.close();
                if(pst != null) pst.close();
                if (con != null) con.close();
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return conversationId;
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


}
