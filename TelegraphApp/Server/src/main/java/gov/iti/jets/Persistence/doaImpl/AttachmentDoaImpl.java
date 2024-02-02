package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.Attachment;
import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.UserStatus;
import gov.iti.jets.Persistence.dao.AttachmentDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachmentDoaImpl implements AttachmentDao {

    //TODO yousef
    @Override
    public Attachment getAttachmentByMessageId(int messageId) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Attachment attachment = null;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            String sql = "select * from attachment where message_id=?;";
            pst = con.prepareStatement(sql);
            pst.setInt(1,messageId);

            rs = pst.executeQuery();

            while (rs.next()){
                 attachment = new Attachment(rs.getInt("attachment_id"),
                        rs.getInt("message_id"),rs.getString("attachment_name"),
                                rs.getString("attachment_type"));
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
        return attachment;
    }
//TODO yousef
    @Override
    public void add(Attachment entity)
    {
        Connection con = null;
        PreparedStatement pst = null;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            con.setAutoCommit(true);
            String sql = "insert into attachment (message_id,attachment_name,attachment_type)\n" +
                    "values (?,?,?);";
            pst = con.prepareStatement(sql);
            pst.setInt(1,entity.getMessageId());
            pst.setString(2,entity.getAttachmentName());
            pst.setString(3,entity.getAttachmentType());

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
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }


    }

    @Override
    public List<Attachment> getAll() {
        return null;
    }

    @Override
    public void update(Attachment entity) {

    }

    @Override
    public void delete(Attachment entity) {

    }
}
