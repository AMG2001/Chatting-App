package gov.iti.jets.Model.User;

import DTO.User.ContactDTO;
import gov.iti.jets.Model.ConversationModel;

public class ContactModel {
    private String phoneNumber;
    private String status;
    private String name;
    private ConversationModel conversation; // Assuming you have a ConversationModel class
    private byte[] profilepic;

    public ContactModel(ContactDTO dto) {
        this.phoneNumber = dto.getPhoneNumber();
        this.status = dto.getStatus();
        this.name = dto.getName();
        this.conversation = new ConversationModel(); // Assuming ConversationDTO has a constructor
        this.profilepic = dto.getProfilepic();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConversationModel getConversation() {
        return conversation;
    }

    public void setConversation(ConversationModel conversation) {
        this.conversation = conversation;
    }

    public byte[] getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(byte[] profilepic) {
        this.profilepic = profilepic;
    }
}