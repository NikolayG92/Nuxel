package com.example.nuxel.userservice.services.impl;

import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.repositories.UserRepository;
import com.example.nuxel.userservice.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
