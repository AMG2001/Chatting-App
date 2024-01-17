package gov.iti.jets.domain;

import java.util.Date;

public class User {
    private String phoneNumber;
    private String name;
    private String email;
    private String country;
    private String userStatus;
    private String gender;
    private String bio;
    private String imageReference;
    private Date dob;

    public User(String phoneNumber, String name, String email, String country, String userStatus,
                String gender, String bio, String imageReference, Date dob) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.country = country;
        this.userStatus = userStatus;
        this.gender = gender;
        this.bio = bio;
        this.imageReference = imageReference;
        this.dob = dob;
    }


    // Getters and Setters
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", gender='" + gender + '\'' +
                ", bio='" + bio + '\'' +
                ", imageReference='" + imageReference + '\'' +
                ", dob=" + dob +
                '}';
    }
}
