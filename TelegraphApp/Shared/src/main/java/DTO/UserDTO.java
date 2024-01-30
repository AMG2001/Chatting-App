package DTO;

import javafx.scene.image.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class UserDTO implements Serializable {
    private String phoneNumber;
    private String name;
    private String email;
    private String password;
    private String dateOfBirth;
    private String country;
    private String gender;
    private String bio;
    private String status;
    private byte[] serializedImage;

    public UserDTO(String phoneNumber, String name, String email, String password, String dataOfBirth, String country, String gender, String bio, String status, Image image) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dataOfBirth;
        this.country = country;
        this.gender = gender;
        this.bio = bio;
        this.status = status;
        this.serializedImage = serializeImage(image);
    }
    public UserDTO(String phoneNumber, String name, String email, String password, String dataOfBirth, String country, String gender, String bio, String status, byte[] image) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dataOfBirth;
        this.country = country;
        this.gender = gender;
        this.bio = bio;
        this.status = status;
        this.serializedImage = image;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getSerializedImageURL() {
        return serializedImage;
    }

    private byte[] serializeImage(javafx.scene.image.Image image) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            objectOutputStream.writeObject(image);

            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setSerializedImage(byte[] serializedImage) {
        this.serializedImage = serializedImage;
    }
}
