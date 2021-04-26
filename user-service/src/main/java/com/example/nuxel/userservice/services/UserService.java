package com.example.nuxel.userservice.services;

import com.example.nuxel.userservice.exceptions.PasswordDoNotMatchException;
import com.example.nuxel.userservice.exceptions.UserNotFoundException;
import com.example.nuxel.userservice.model.bindingModels.LoginBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserEditBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.model.view.LoginViewModel;
import com.example.nuxel.userservice.model.view.RegisterViewModel;
import com.example.nuxel.userservice.services.serviceModels.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<UserServiceModel> getAllUsers();

    UserServiceModel findUserByUsername(String username);

    RegisterViewModel registerUser(UserRegisterBindingModel userRegisterBindingModel);

    LoginViewModel loginUser(LoginBindingModel loginBindingModel) throws UserNotFoundException;


    UserServiceModel findUserByEmail(String email);

    UserServiceModel changeProfilePicture(String name, MultipartFile file) throws IOException, UserNotFoundException;

    UserServiceModel changePassword(String oldPassword, String newPassword, String username) throws UserNotFoundException, PasswordDoNotMatchException;

    UserServiceModel changeProfileDetails(UserEditBindingModel userEditBindingModel, String name) throws UserNotFoundException;
}
