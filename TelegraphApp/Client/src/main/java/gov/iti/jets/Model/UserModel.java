package gov.iti.jets.Model;

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

    public UserModel() {

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
