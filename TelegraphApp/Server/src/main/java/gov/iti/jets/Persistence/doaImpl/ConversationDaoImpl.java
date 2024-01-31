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
    public void add(Conversation entity) {

    }

    @Override
    public List<Conversation> getAll() {
        return null;
    }

    @Override
    public void update(Conversation entity) {

    }

    @Override
    public void delete(Conversation entity) {

    }

    @Override
    public Conversation getById(String s) {
        return null;
    }
}
