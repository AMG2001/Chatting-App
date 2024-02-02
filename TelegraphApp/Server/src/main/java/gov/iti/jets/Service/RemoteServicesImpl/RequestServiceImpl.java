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

    }

    @Override
    public void updateRequest(RequestDTO request) throws RemoteException {

    }

    @Override
    public ArrayList<RequestDTO> getAllRequest(String Phone) throws RemoteException {
        return null;
    }
}
