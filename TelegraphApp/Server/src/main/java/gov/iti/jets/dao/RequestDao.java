package gov.iti.jets.dao;

import gov.iti.jets.domain.FriendRequests;

import java.util.List;

public class RequestDao {
    public void createRequest(FriendRequests request) {
        // Implementation goes here
    }

    public List<FriendRequests> getRequestsBySender(String phoneNumber) {
        // Implementation goes here
        return null;
    }

    public List<FriendRequests> getRequestsByReceiver(String phoneNumber) {
        // Implementation goes here
        return null;
    }

    public void updateRequestStatus(FriendRequests request) {
        // Implementation goes here
    }
}

