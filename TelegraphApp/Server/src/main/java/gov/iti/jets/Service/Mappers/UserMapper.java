package gov.iti.jets.Service.Mappers;

import DTO.Group.GroupMemberDTO;
import DTO.User.UpdatedUserDTO;
import DTO.User.UserDTO;
import gov.iti.jets.Domain.User;
import gov.iti.jets.Domain.enums.Gender;
import gov.iti.jets.Domain.enums.UserStatus;

public class UserMapper {

    public static UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setDateOfBirth(user.getDob());
        userDTO.setCountry(user.getCountry());
        userDTO.setGender(user.getGender().name());
        userDTO.setBio(user.getBio());
        userDTO.setStatus(user.getStatus().name());

        return userDTO;
    }

    public static User userDTOToUser(UserDTO userDTO){
        User user = new User();

        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCountry(userDTO.getCountry());
        user.setBio(userDTO.getBio());
        user.setGender(Gender.valueOf(userDTO.getGender()));
        user.setDob(userDTO.getDateOfBirth());
        user.setStatus(UserStatus.valueOf(userDTO.getStatus()));

        return user;
    }


    public static User updatedUserDTOToUser(UpdatedUserDTO updatedUserDTO) {
        User user = new User();
        user.setPhoneNumber(updatedUserDTO.getPhoneNumber());
        user.setName(updatedUserDTO.getName());
        user.setEmail(updatedUserDTO.getEmail());
        user.setPassword(updatedUserDTO.getPassword());
        user.setCountry(updatedUserDTO.getCountry());
        user.setBio(updatedUserDTO.getBio());
        user.setGender(Gender.valueOf(updatedUserDTO.getGender()));
        user.setDob(updatedUserDTO.getDateOfBirth());

        return user;
    }

    public static GroupMemberDTO userToGroupMemberDTO(User user){
        GroupMemberDTO groupMemberDTO = new GroupMemberDTO();

        groupMemberDTO.setPhoneNumber(user.getPhoneNumber());
        groupMemberDTO.setName(user.getName());
        groupMemberDTO.setStatus(user.getStatus().name());

        return groupMemberDTO;
    }



}
