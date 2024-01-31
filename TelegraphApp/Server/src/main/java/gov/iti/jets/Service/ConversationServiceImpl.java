package gov.iti.jets.Service;

import DTO.ConversationDTO;
import RemoteInterfaces.RemoteConversationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ConversationServiceImpl extends UnicastRemoteObject implements RemoteConversationService {
    protected ConversationServiceImpl() throws RemoteException {
    }

    @Override
    public ArrayList<ConversationDTO> getAllConversationsForUser(String userPhone) throws RemoteException {
        return null;
    }
}
