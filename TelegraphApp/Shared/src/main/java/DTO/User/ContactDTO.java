package DTO.User;

import DTO.ConversationDTO;

import java.io.Serializable;
import java.util.Arrays;

public class ContactDTO implements Serializable {
    private String phoneNumber;
    private String status;
    private String name;
    ConversationDTO conversation;
    private byte[] profilepic;

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
        this.conversation = conversation;
    }
    public ContactDTO(String phoneNumber, String status, String name, ConversationDTO conversation, byte[] profilepic) {
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.name = name;
        this.conversation = conversation;
        this.profilepic = profilepic;
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

    public byte[] getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(byte[] profilepic) {
        this.profilepic = profilepic;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "phonenumber='" + phoneNumber + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", profilepic=" + Arrays.toString(profilepic) +
                '}';
    }
}
