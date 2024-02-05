package gov.iti.jets.Service.Mapstructs;

import DTO.ContactDTO;
import gov.iti.jets.Domain.User;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "name", target = "name")
    ContactDTO userToContactDTO(User user);
}
