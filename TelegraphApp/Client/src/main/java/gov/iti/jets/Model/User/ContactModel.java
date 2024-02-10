package gov.iti.jets.Model.User;

import DTO.User.ContactDTO;
import gov.iti.jets.Controllers.services.ConversationsServicesClass;
import gov.iti.jets.Model.ConversationModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.scene.paint.Color;

public class ContactModel {
    public Color getStatusCircleColor() {
        return statusCircleColor.get();
    }

    public ObjectProperty<Color> statusCircleColorProperty() {
        return statusCircleColor;
    }

    private StringProperty phoneNumber, status, name;
    private ConversationModel conversation; // Assuming you have a ConversationModel class
    private ObjectProperty<byte[]> profilepic;
    private ObjectProperty<Color> statusCircleColor;

    public ContactModel(ContactDTO dto) {
        this.phoneNumber = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.profilepic = new SimpleObjectProperty<>();
        this.statusCircleColor = new SimpleObjectProperty<>();
        this.phoneNumber.set(dto.getPhoneNumber());
        this.status.set(dto.getStatus());
        Color newColor = ConversationsServicesClass.setConversationsCircleColor(status.get());
        this.statusCircleColor.set(newColor);
        this.status.addListener((observable, oldValue, newValue) -> {
            this.statusCircleColor.set(newColor);
        });
        this.name.set(dto.getName());
        this.conversation = new ConversationModel(dto.getConversation());
        this.profilepic.set(dto.getProfilepic());
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getName() {
        return name.get();
    }

    public ConversationModel getConversation() {
        return conversation;
    }

    public byte[] getProfilepic() {
        return profilepic.get();
    }

    @Override
    public String toString() {
        return "ContactModel{" + "phoneNumber='" + getPhoneNumber() + '\'' + ", status='" + getStatus() + '\'' + ", name='" + getName() + '\'' + ", conversation id =" + getConversation().getConversationId() + ", conversation name =" + getConversation().getType() + '}';
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setConversation(ConversationModel conversation) {
        this.conversation = conversation;
    }

    public void setProfilepic(byte[] profilepic) {
        this.profilepic.set(profilepic);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}