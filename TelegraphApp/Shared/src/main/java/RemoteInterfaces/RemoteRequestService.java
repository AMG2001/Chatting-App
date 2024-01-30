package RemoteInterfaces;

import DTO.RequestDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteRequestService extends Remote {
    void sendRequest(RequestDTO request)throws RemoteException;
    void updateRequest(RequestDTO request)throws RemoteException;
    ArrayList<RequestDTO> getAllRequest(String Phone)throws RemoteException;

}
