package gov.iti.jets.Service.Mappers;

import DTO.User.ContactDTO;
import gov.iti.jets.Domain.User;

public class ContactMapper {

    public static ContactDTO userToContactDTO(User user){
        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setPhoneNumber(user.getPhoneNumber());
        contactDTO.setStatus(user.getStatus().name());
        contactDTO.setName(user.getName());

        return contactDTO;
    }
}
