package gov.iti.jets.AdminPanel.Models;

import gov.iti.jets.Domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserModel mapToUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setName(user.getName());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setCountry(user.getCountry());
        userModel.setStatus(user.getStatus());
        userModel.setGender(user.getGender());
        userModel.setBio(user.getBio());
        userModel.setPicture(user.getPicture());
        userModel.setDob(user.getDob());
        return userModel;
    }

    public static User mapToUser(UserModel userModel) {
        User user = new User();
        user.setPhoneNumber(userModel.getPhoneNumber());
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        user.setCountry(userModel.getCountry());
        user.setStatus(userModel.getStatus());
        user.setGender(userModel.getGender());
        user.setBio(userModel.getBio());
        user.setPicture(userModel.getPicture());
        user.setDob(userModel.getDob());
        return user;
    }

    public static List<UserModel> mapToUserModels(List<User> users) {
        return users.stream()
                .map(UserMapper::mapToUserModel)
                .collect(Collectors.toList());
    }

    public static List<User> mapToUsers(List<UserModel> userModels) {
        return userModels.stream()
                .map(UserMapper::mapToUser)
                .collect(Collectors.toList());
    }
}