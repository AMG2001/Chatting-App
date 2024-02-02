package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.RequestDTO;
import RemoteInterfaces.RemoteRequestService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RequestServiceImpl extends UnicastRemoteObject implements RemoteRequestService {

    public RequestServiceImpl() throws RemoteException {
    }

    @Override
    public void sendRequest(RequestDTO request) throws RemoteException {
        //Todo Yousef - Check if a request already exists. If not add the request to the DB.
        /**
         * 1) Check if receiver phone exists
         *  User user = UserDao.getByID is not Null
         * 2) Check if a request has already been sent between Sender & Reciever
         *  RequestDAO.checkifexists(Request)
         * 3) Map RequestDTO to Request Domain
         */


    }

    @Override
    public void updateRequest(RequestDTO request) throws RemoteException {

    }

    @Override
    public ArrayList<RequestDTO> getAllRequest(String Phone) throws RemoteException {
        //Todo Retrieve all the requests recieved by the user and return them after mapping the domain objects to DTO
        return null;
    }
}
