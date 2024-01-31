package gov.iti.jets.Domain;

import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.UserStatus;

import java.time.LocalDate;

public class User {
    private String phone_number;
    private String name;
    private String email;
    private String password;
    private String country;
    private UserStatus status;
    private Gender gender;
    private String bio;
    private String picture;
    private LocalDate dob;

    public User() {

    }

    public User(String phone_number, String name, String email, String password, String country, UserStatus status, Gender gender, String bio, String picture, LocalDate dob) {
        this.phone_number = phone_number;
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.status = status;
        this.gender = gender;
        this.bio = bio;
        this.picture = picture;
        this.dob = dob;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone_number='" + phone_number + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", status=" + status +
                ", gender=" + gender +
                ", bio='" + bio + '\'' +
                ", picture='" + picture + '\'' +
                ", dob=" + dob +
                '}';
    }
}
