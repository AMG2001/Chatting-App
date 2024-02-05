package DTO.Group;

import java.io.Serializable;
import java.util.List;

public class GroupCreationDTO implements Serializable {

    private String groupName;
    private List<String> groupMembers; //list of phone numbers
    private String groupFounder;
    private byte[] groupImg;

    public GroupCreationDTO(String groupName, List<String> groupMembers, String groupFounder, byte[] groupImg) {
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

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<String> groupMembers) {
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
