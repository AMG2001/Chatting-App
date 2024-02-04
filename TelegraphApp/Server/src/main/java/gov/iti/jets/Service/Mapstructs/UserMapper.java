package gov.iti.jets.Service.Mapstructs;
import DTO.UpdatedUserDTO;
import DTO.UserDTO;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.UserStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "dob", target = "dateOfBirth")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "status", target = "status")
    UserDTO userToUserDTO(User user);

    // Reverse mapping
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "dateOfBirth", target = "dob")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "status", target = "status")
    User userDTOToUser(UserDTO userDTO);

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "dateOfBirth", target = "dob")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "bio", target = "bio")
    User updatedUserDTOToUser(UpdatedUserDTO updatedUserDTO);


    default String mapGender(Gender gender) {
        // Convert the Gender enum to String
        return (gender != null) ? gender.name() : null;
    }

    default Gender mapGender(String gender) {
        // Convert the String to Gender enum
        return (gender != null) ? Gender.valueOf(gender) : null;
    }

    default String mapStatus(UserStatus status) {
        // Convert the UserStatus enum to String
        return (status != null) ? status.name() : null;
    }

    default UserStatus mapStatus(String status) {
        // Convert the String to UserStatus enum
        return (status != null) ? UserStatus.valueOf(status) : null;
    }



}

