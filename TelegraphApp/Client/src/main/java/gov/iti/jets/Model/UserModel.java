package gov.iti.jets.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class UserModel {
    StringProperty userPhone = new SimpleStringProperty();
    StringProperty userName = new SimpleStringProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty country = new SimpleStringProperty();
    StringProperty status = new SimpleStringProperty();
    StringProperty gender = new SimpleStringProperty();
    StringProperty bio = new SimpleStringProperty();
    ObjectProperty<LocalDate> dob = new SimpleObjectProperty<LocalDate>();
    ObjectProperty<Image> profilePic = new SimpleObjectProperty<Image>();
}
