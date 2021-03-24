package com.example.nuxel.userservice.services;

import com.example.nuxel.userservice.model.entities.User;

public interface UserService {
    User findById(String id);
}
