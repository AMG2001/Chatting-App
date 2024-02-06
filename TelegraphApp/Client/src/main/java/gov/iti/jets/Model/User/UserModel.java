package gov.iti.jets.Model.User;

import DTO.User.UserDTO;
import gov.iti.jets.Controllers.services.FileConverter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class UserModel {
    public StringProperty userPhone = new SimpleStringProperty();
    public StringProperty userName = new SimpleStringProperty();
    public StringProperty email = new SimpleStringProperty();
    public StringProperty country = new SimpleStringProperty();
    public StringProperty status = new SimpleStringProperty();
    public StringProperty gender = new SimpleStringProperty();
    public StringProperty bio = new SimpleStringProperty();
    public ObjectProperty<LocalDate> dob = new SimpleObjectProperty<LocalDate>();
    public ObjectProperty<Image> profilePic = new SimpleObjectProperty<Image>();
    private StringProperty password = new SimpleStringProperty();

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone.set(userPhone);
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public void setBio(String bio) {
        this.bio.set(bio);
    }

    public void setDob(LocalDate dob) {
        this.dob.set(dob);
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic.set(profilePic);
    }

    public UserModel() {

    }

    public UserModel(String userPhone, String userName, String email, String country, String status, String gender, String bio, LocalDate dob,String password, Image profilePic) {
        this.userPhone.set(userPhone);
        this.userName.set(userName);
        this.email.set(email);
        this.country.set(country);
        this.status.set(status);
        this.gender.set(gender);
        this.bio.set(bio);
        this.dob.set(dob);
        this.password.set(password);
        this.profilePic.set(profilePic);
    }

    public UserModel(UserDTO userDTO) {
        this.userName.set(userDTO.getName());
        this.userPhone.set(userDTO.getPhoneNumber());
        this.email.set(userDTO.getEmail());
        this.country.set(userDTO.getCountry());
        this.status.set(userDTO.getStatus());
        this.gender.set(userDTO.getGender());
        this.bio.set(userDTO.getBio());
        this.dob.set(userDTO.getDateOfBirth());
        this.password.set(userDTO.getPassword());
        this.profilePic.set(FileConverter.convert_bytesToImage(userDTO.getSerializedImageURL()));
    }

    public String getUserPhone() {
        return userPhone.get();
    }

    public String getUserName() {
        return userName.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getCountry() {
        return country.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getBio() {
        return bio.get();
    }

    public LocalDate getDob() {
        return dob.get();
    }

    public Image getProfilePic() {
        return profilePic.get();
    }
}
