package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.RequestDTO;
import RemoteInterfaces.RemoteRequestService;
import gov.iti.jets.Domain.ContactRequest;
import gov.iti.jets.Persistence.dao.ContactRequestDao;
import gov.iti.jets.Persistence.doaImpl.ContactRequestDaoImpl;
import gov.iti.jets.Service.Mapstructs.RequestMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RequestServiceImpl extends UnicastRemoteObject implements RemoteRequestService {

    public RequestServiceImpl() throws RemoteException {

    }

    @Override
    public void sendRequest(RequestDTO request) throws RemoteException {
        //Todo Yousef - Check if a request already exists. If not add the request to the DB.
        /**
         * 1) Check if receiver phone exists---------------- CRITICAL
         * SEND NOTIFICATION IF PHONE NUMBER DOES NOT EXIT
         *  User user = UserDao.getByID is not Null
         * 2) Check if a request has already been sent between Sender & Reciever
         *  RequestDAO.checkifexists(Request)
         * 3) Map RequestDTO to Request Domain object
         * 4) Save Request domain object in database--
         * 4) Send notification to SENDER that it has been sent or NOT sent
         * 5) Send Request to reciever using callback
         */


    }

    @Override
    public void updateRequest(RequestDTO request) throws RemoteException {
        //TODO moataz
        /* Update the request to be accepted or rejected. IF accepted then use DAO to
            Create a conversation- Add relationship between user (Alter User table)
            If Rejected update Request Table
            Sent Notifications to sender that request was accepted or rejected.
        */

    }

    @Override
    public ArrayList<RequestDTO> getAllRequest(String phone) throws RemoteException {

        ContactRequestDao requestDao = new ContactRequestDaoImpl();
        List<ContactRequest> requests = requestDao.getRequestsByReceiver(phone);
        List<RequestDTO> requestDTOs = new ArrayList<>();
        for(ContactRequest request : requests)
        {
            RequestDTO requestDto = RequestMapper.INSTANCE.contactRequestToRequestDto(request);
            requestDTOs.add(requestDto);
        }
        return (ArrayList<RequestDTO>) requestDTOs;

    }
}
