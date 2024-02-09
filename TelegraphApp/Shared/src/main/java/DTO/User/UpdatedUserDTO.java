package DTO.User;

import java.io.Serializable;
import java.time.LocalDate;

public class UpdatedUserDTO implements Serializable {
    private static final long serialVersionUID = 987654321000L;
    private String phoneNumber;
    private String name;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String country;
    private String gender;
    private String bio;
    private byte[] serializedImage;
    private Boolean isPicChanged;

    public UpdatedUserDTO(String phoneNumber, String name, String email, String password, LocalDate dateOfBirth, String country, String gender, String bio, byte[] serializedImage, Boolean isPicChanged) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.gender = gender;
        this.bio = bio;
        this.serializedImage = serializedImage;
        this.isPicChanged = isPicChanged;
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

    public byte[] getSerializedImage() {
        return serializedImage;
    }

    public void setSerializedImage(byte[] serializedImage) {
        this.serializedImage = serializedImage;
    }

    public Boolean getPicChanged() {
        return isPicChanged;
    }

    public void setPicChanged(Boolean picChanged) {
        isPicChanged = picChanged;
    }
}
