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
    @Override
    public List<Attachment> getAllAttachmentsByConversationId(int conversationId) {
        List<Attachment> attachmentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            String sql = "select * from attachment where conversation_id = ? ;";
            pst = con.prepareStatement(sql);
            pst.setInt(1,conversationId);

            rs = pst.executeQuery();

            while (rs.next()){
                Attachment attachment = new Attachment(rs.getInt("attachment_id"),
                        rs.getString("attachment_name"),
                        rs.getString("attachment_type"),
                        rs.getString("attachment_location"),
                        rs.getInt("conversation_id")
                );
                attachmentList.add(attachment);
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
        return attachmentList;

    }

    @Override
    public Attachment getAttachmentByConversationIdAndAttachmentId(int conversationId,int attachmentId) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Attachment attachment = null;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            String sql = "select * from attachment where conversation_id = ? and attachment_id = ? ;";
            pst = con.prepareStatement(sql);
            pst.setInt(1,conversationId);
            pst.setInt(2,attachmentId);

            rs = pst.executeQuery();

            while (rs.next()){
                attachment = new Attachment(rs.getInt("attachment_id"),
                        rs.getString("attachment_name"),
                        rs.getString("attachment_type"),
                        rs.getString("attachment_location"),
                        rs.getInt("conversation_id")
                        );
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
        return attachment;

    }
    @Override
    public void add(Attachment entity)
    {
        Connection con = null;
        PreparedStatement pst = null;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            con.setAutoCommit(true);
            String sql = "insert into attachment (conversation_id,attachment_name,attachment_type,attachment_location)\n" +
                    "values (?,?,?,?);";
            pst = con.prepareStatement(sql);

            pst.setInt(1,entity.getConversationId());
            pst.setString(2,entity.getAttachmentName());
            pst.setString(3,entity.getAttachmentType());
            pst.setString(4,entity.getAttachmentLocation());

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
