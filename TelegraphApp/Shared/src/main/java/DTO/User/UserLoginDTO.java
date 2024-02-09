package DTO.User;

import java.io.Serializable;

public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = Long.MAX_VALUE;
    private String phoneNumber;
    private String password;

    public UserLoginDTO(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
