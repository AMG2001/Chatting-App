package DTO.Group;

import java.io.Serializable;

public class GroupCreationDTO implements Serializable {

    private String groupName;
    private String[] groupMembers;
    private String groupFounder;
    private byte[] groupImg;

    public GroupCreationDTO(String groupName, String[] groupMembers, String groupFounder, byte[] groupImg) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.groupFounder = groupFounder;
        this.groupImg = groupImg;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String[] getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(String[] groupMembers) {
        this.groupMembers = groupMembers;
    }

    public String getGroupFounder() {
        return groupFounder;
    }

    public void setGroupFounder(String groupFounder) {
        this.groupFounder = groupFounder;
    }

    public byte[] getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(byte[] groupImg) {
        this.groupImg = groupImg;
    }
}
