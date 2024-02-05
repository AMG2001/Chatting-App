package RemoteInterfaces;

import DTO.Request.RequestSendDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteRequestService extends Remote {
    void sendRequest(RequestSendDTO request)throws RemoteException;
    void updateRequest(RequestSendDTO request)throws RemoteException;
    ArrayList<RequestSendDTO> getAllRequest(String Phone)throws RemoteException;

}
