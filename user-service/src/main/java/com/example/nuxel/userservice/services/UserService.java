package com.example.nuxel.userservice.services;

import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.services.serviceModels.UserServiceModel;

public interface UserService {
    User findById(String id);

    void registerUser(UserServiceModel user);
}
