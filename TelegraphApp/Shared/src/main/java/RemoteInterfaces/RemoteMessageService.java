package RemoteInterfaces;

import DTO.MessageDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMessageService extends Remote {
    void sendMessage(MessageDTO message) throws RemoteException;
}
