package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.ConversationDTO;
import RemoteInterfaces.RemoteConversationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ConversationServiceImpl extends UnicastRemoteObject implements RemoteConversationService {
    public ConversationServiceImpl() throws RemoteException {
    }

    @Override
    public ConversationDTO getConversationForUserPhone(String userPhone) throws RemoteException {

        return null;
    }
}
