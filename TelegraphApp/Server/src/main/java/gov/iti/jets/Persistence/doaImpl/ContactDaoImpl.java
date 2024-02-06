package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.Contact;
import gov.iti.jets.Persistence.dao.ContactDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    @Override
    public boolean checkIfAlreadyContacts(String userPhone, String contactPhone) {
        //if it returns false .... the two phonenumbers are not contacts
        //if returns true ... the two phone numbers are contacts
        Connection con = null;
        PreparedStatement pst = null;
        boolean alreadyContacts = false;
        try{
            con = DBConnectionPool.DATASOURCE.getConnection();
            con.setAutoCommit(true);
            String sql = "select * from contact where user_phone = ?\n" +
                    "and contact_phone = ?;";
            pst = con.prepareStatement(sql);

            pst.setString(1,userPhone);
            pst.setString(2,contactPhone);

            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                System.out.println("Row found!");
                alreadyContacts = true;
            } else {
                // The ResultSet is empty
                System.out.println("No rows found!");
            }

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
        return alreadyContacts;
    }

    @Override
    public void add(Contact entity) {

    }

    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public void update(Contact entity) {

    }

    @Override
    public void delete(Contact entity) {

    }
}
