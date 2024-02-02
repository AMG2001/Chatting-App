package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.UserConversation;
import gov.iti.jets.Persistence.dao.UserConversationDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class UserConversationDoaImpl implements UserConversationDao {
    @Override
    public void add(UserConversation entity) {

    }

    @Override
    public List<UserConversation> getAll() {
        return null;
    }

    @Override
    public void update(UserConversation entity) {

    }

    @Override
    public void delete(UserConversation entity) {

    }

    @Override
    public void addGroupMembers(int conversationId, List<String> membersPhones) {
        Connection con=null;
        PreparedStatement pst = null;

        try {
            con = DBConnectionPool.DATASOURCE.getConnection();

            con.setAutoCommit(false);

            String sql = "INSERT INTO User_Conversation (phone_number, conversation_id, join_date)\n" +
                    "VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);

            for(String memberPhone:membersPhones) {
                pst.setString(1,memberPhone);
                pst.setInt(2,conversationId);

                java.sql.Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                pst.setTimestamp(3, currentTimestamp);

                pst.executeUpdate();
            }
            con.commit();
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
                if(pst != null) pst.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
