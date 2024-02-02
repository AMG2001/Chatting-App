package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.ConversationDTO;
import RemoteInterfaces.RemoteConversationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ConversationServiceImpl extends UnicastRemoteObject implements RemoteConversationService {
    protected ConversationServiceImpl() throws RemoteException {
    }

    @Override
    public ConversationDTO getAllConversationForUser(String userPhone) throws RemoteException {

        return null;
    }
}
