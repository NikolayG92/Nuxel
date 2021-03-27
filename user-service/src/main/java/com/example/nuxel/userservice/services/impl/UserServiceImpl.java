package com.example.nuxel.userservice.services.impl;

import com.example.nuxel.userservice.constants.RoleConstants;
import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.repositories.UserRepository;
import com.example.nuxel.userservice.services.RoleService;
import com.example.nuxel.userservice.services.UserService;
import com.example.nuxel.userservice.services.serviceModels.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, ModelMapper modelMapper, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(new HashSet<>());
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority((RoleConstants.ROOT)));
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority((RoleConstants.ADMIN)));
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority((RoleConstants.MODERATOR)));
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority((RoleConstants.USER)));
        } else {
            userServiceModel.setAuthorities(new HashSet<>());
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority((RoleConstants.USER)));
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        user.setRegisteredOn(LocalDateTime.now());
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findUserByUsername(username)
                .orElseThrow
                        (() ->
                                new UsernameNotFoundException(String.format("%s user not found", username)));
    }
}
