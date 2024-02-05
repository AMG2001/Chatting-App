package gov.iti.jets.Model.User;

import DTO.User.UserLoginDTO;

import java.io.Serializable;

public class UserLoginModel {
    private String phoneNumber;
    private String password; // Handle securely
    public UserLoginModel(UserLoginDTO dto) {
        this.phoneNumber = dto.getPhoneNumber();
        this.password = dto.getPassword(); // Handle securely if needed
    }

    // Getters and setters for all fields (not shown for brevity)

    @Override
    public String toString() {
        return "UserLoginModel{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}'; // Exclude password for security
    }
}
