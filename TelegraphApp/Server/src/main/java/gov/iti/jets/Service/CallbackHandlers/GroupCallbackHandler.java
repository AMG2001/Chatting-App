package gov.iti.jets.Service.CallbackHandlers;

import DTO.GroupDTO;
import RemoteInterfaces.callback.RemoteCallbackInterface;

import java.rmi.RemoteException;
import java.util.List;

public class GroupCallbackHandler {
    public void createGroup(GroupDTO newGroup, List<RemoteCallbackInterface> groupMembers) {
        try {
            for(RemoteCallbackInterface groupMember: groupMembers)
            {
                groupMember.addGroup(newGroup);
            }
        } catch (RemoteException e) {
            System.out.println("Error adding new Contact : " + e.getMessage());
            System.out.println("Contact Name" + newGroup.getConversation().getConversationName() + " failed to be added as friend");
        }


    }
}