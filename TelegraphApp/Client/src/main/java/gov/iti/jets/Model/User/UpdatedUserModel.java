package gov.iti.jets.Model.User;

import DTO.User.UpdatedUserDTO;

import java.io.Serializable;
import java.time.LocalDate;

public class UpdatedUserModel implements Serializable {
    private String phoneNumber;
    private String name;
    private String email;
    private String password; // Consider handling password securely
    private LocalDate dateOfBirth;
    private String country;
    private String gender;
    private String bio;
    private byte[] image; // Assuming image storage format
    private boolean picChanged; // Using boolean for clarity

    public UpdatedUserModel(UpdatedUserDTO dto) {
        this.phoneNumber = dto.getPhoneNumber();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = dto.getPassword(); // Handle securely if needed
        this.dateOfBirth = dto.getDateOfBirth();
        this.country = dto.getCountry();
        this.gender = dto.getGender();
        this.bio = dto.getBio();
        this.image = dto.getSerializedImage();
        this.picChanged = dto.getPicChanged();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isPicChanged() {
        return picChanged;
    }

    public void setPicChanged(boolean picChanged) {
        this.picChanged = picChanged;
    }
}
