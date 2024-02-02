package gov.iti.jets.Persistence.doaImplTest;

import gov.iti.jets.Persistence.doaImpl.UserConversationDoaImpl;

import java.util.ArrayList;
import java.util.Arrays;

public class UserConversationDoaImplTest {
    UserConversationDoaImpl userConversationDoa = new UserConversationDoaImpl();

    private void addGroupMembers(){
        userConversationDoa.addGroupMembers(8,new ArrayList<>(Arrays.asList("555555555","987654321")));
    }
    public static void main(String[] args) {
        UserConversationDoaImplTest userConversationDoaImplTest = new UserConversationDoaImplTest();
        userConversationDoaImplTest.addGroupMembers();
    }
}
