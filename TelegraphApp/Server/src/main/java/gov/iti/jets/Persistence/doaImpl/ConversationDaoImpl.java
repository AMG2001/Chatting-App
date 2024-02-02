package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversationDaoImpl implements ConversationDao {
    @Override
    public List<Conversation> getGroupConversationsByPhone(String phone) {
        List<Conversation> groups= new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            con = DBConnectionPool.DATASOURCE.getConnection();

            String sql = "select c.conversation_id As group_id, c.conversation_img As group_img , c.conversation_name As group_name " +
                        "from User u, Conversation c, User_Conversation uc " +
                        "where uc.conversation_id= c.conversation_id and uc.phone_number= u.phone_number " +
                        "and u.phone_number = ? " +
                        "and c.type= ?;";
            pst = con.prepareStatement(sql);

            pst.setString(1,phone);
            pst.setString(2,"GROUP");

            rs = pst.executeQuery();

            while (rs.next()){
                int group_id = rs.getInt("group_id");
                String group_img = rs.getString("group_img");
                String group_name = rs.getString("group_name");

                Conversation group = new Conversation();
                group.setConversationId(group_id);
                group.setConversationImage(group_img);
                group.setConversationName(group_name);

                groups.add(group);
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
        return groups;
    }

    @Override
    public List<String> getConversationParticipants(int conversationId) {
        return null;
    }

    @Override
    public void add(Conversation entity) {

    }

    @Override
    public List<Conversation> getAll() {
        return null;
    }


    //TODO yousef
    @Override
    public void update(Conversation entity) {

    }

    @Override
    public void delete(Conversation entity) {

    }

    //group or individual conversation
    //TODO yousef
    @Override
    public Conversation getById(Integer conversationId) {
        return null;
    }

    @Override
    public int getIndividualConversationId(String userPhone,String contactPhone){

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
                if (rs != null) rs.close();
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


    @Override
    public void createGroupConversation(String userPhone,Conversation group) {

        Connection con=null;
        try{
            con=DBConnectionPool.DATASOURCE.getConnection();

            con.setAutoCommit(false);

            int groupId = createGroup(con, group);
            addUserToGroup(userPhone,groupId);

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
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private int createGroup(Connection con, Conversation group) throws SQLException{
        int groupId = 0;

        String sql = "insert into Conversation (conversation_img, conversation_name,type)\n" +
                    "values (?, ?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, group.getConversationImage());
            pst.setString(2, group.getConversationName());
            pst.setString(3,"GROUP");
            pst.executeUpdate();

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    groupId = generatedKeys.getInt(1);
                }
            }
        }
        return groupId;
    }

    private void addUserToGroup(String userPhone, int groupId) throws SQLException{
        String sql = "INSERT INTO User_Conversation (phone_number, conversation_id, join_date)\n" +
                "VALUES (?, ?, ?)";
    }

    public static void main(String[] args) {
        ConversationDaoImpl conversationDao = new ConversationDaoImpl();
        Conversation group = new Conversation();
        group.setConversationImage("im2");
        group.setConversationName("testGroup");
        conversationDao.createGroupConversation("123456789",group);
    }

}
