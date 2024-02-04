package DTO;

import java.io.Serializable;

public class GroupMemberDTO implements Serializable {
    private String phonenumber;
    private String status;
    private String name;
    private byte[] profilepic;

    public GroupMemberDTO(String phonenumber, String status, String name, byte[] profilepic) {
        this.phonenumber = phonenumber;
        this.status = status;
        this.name = name;
        this.profilepic = profilepic;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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