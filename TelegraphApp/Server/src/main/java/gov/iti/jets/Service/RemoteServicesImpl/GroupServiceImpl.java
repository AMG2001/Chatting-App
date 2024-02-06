package gov.iti.jets.Service.RemoteServicesImpl;

import DTO.Group.GroupCreationDTO;
import RemoteInterfaces.RemoteGroupService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class GroupServiceImpl extends UnicastRemoteObject implements RemoteGroupService {
    public GroupServiceImpl() throws RemoteException {
    }

    @Override
    public void createGroup(GroupCreationDTO newGroup) throws RemoteException {
        //TODO moataz

    }
}
