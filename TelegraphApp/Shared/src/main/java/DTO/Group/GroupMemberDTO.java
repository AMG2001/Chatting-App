package DTO.Group;

import java.io.Serializable;

public class GroupMemberDTO implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String phoneNumber;
    private String status;
    private String name;
    private byte[] profilepic;

    public GroupMemberDTO(){

    }
    public GroupMemberDTO(String phoneNumber, String status, String name, byte[] profilepic) {
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.name = name;
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
}
