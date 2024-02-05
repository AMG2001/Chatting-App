package RemoteInterfaces;

import DTO.SentRequestDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteRequestService extends Remote {
    void sendRequest(SentRequestDTO request)throws RemoteException;
    void updateRequest(SentRequestDTO request)throws RemoteException;
    ArrayList<SentRequestDTO> getAllRequest(String Phone)throws RemoteException;

}
