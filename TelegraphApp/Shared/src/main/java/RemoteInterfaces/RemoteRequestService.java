package RemoteInterfaces;

import DTO.Request.RequestRecieveDTO;
import DTO.Request.RequestResponseDTO;
import DTO.Request.RequestSendDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteRequestService extends Remote {
    void sendRequest(RequestSendDTO request)throws RemoteException;
    void updateRequest(RequestResponseDTO request)throws RemoteException;
    ArrayList<RequestRecieveDTO> getAllRequest(String Phone)throws RemoteException;

}
