package gov.iti.jets.Persistence.doaImpl;

import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Domain.enums.RequestStatus;
import gov.iti.jets.Persistence.dao.ContactRequestDao;
import gov.iti.jets.Persistence.mysql.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactRequestDaoImpl implements ContactRequestDao {
    @Override
    public List<ContactRequest> getRequestsBySender(String phoneNumber) {
        return null;
    }

    //TODO youssef
    //I changed the localDateTime to date
    //The database has 5 elements and contact request domain object has 6 elements ... why?
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
                Date responded_at =rs.getDate("responded_at");

                ContactRequest request = new ContactRequest();
                request.setRequestId(request_id);
                request.setSenderPhone(sender_phone);
                request.setReceiverPhone(receiver_phone);
                request.setRequestStatus(RequestStatus.valueOf(status));
                request.setResponseDate(responded_at);

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
                DBConnectionPool.DATASOURCE.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return requests;
    }
    //TODO moataz
    @Override
    public Boolean checkIfRequestExist(ContactRequest request) {
        return null;
    }

    //TODO yousef
    @Override
    public void add(ContactRequest entity) {

    }

    @Override
    public List<ContactRequest> getAll() {
        return null;
    }

    //TODO moataz
    @Override
    public void update(ContactRequest entity) {

    }

    @Override
    public void delete(ContactRequest entity) {

    }
}
