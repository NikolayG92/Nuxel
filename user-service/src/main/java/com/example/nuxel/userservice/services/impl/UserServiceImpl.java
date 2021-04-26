package com.example.nuxel.userservice.services.impl;

import com.example.nuxel.userservice.config.jwt.JwtUtils;
import com.example.nuxel.userservice.exceptions.UserNotFoundException;
import com.example.nuxel.userservice.model.bindingModels.LoginBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.model.entities.ProfileDetails;
import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.model.view.LoginViewModel;
import com.example.nuxel.userservice.model.view.RegisterViewModel;
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

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Set<UserServiceModel> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        Set<UserServiceModel> userServiceModels = new HashSet<>();
        users.forEach(user -> {
            UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
            userServiceModels.add(userServiceModel);
        });
        return userServiceModels;
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {
        User user = this.userRepository.findUserByUsername(username)
                .orElse(null);

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public RegisterViewModel registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            throw new SecurityException("Passwords do not match!");
        }
        this.userRepository.findUserByUsername(userRegisterBindingModel.getUsername()).ifPresent(u -> {
            throw new EntityExistsException(String.format("User with username '%s' already exists.", userRegisterBindingModel.getUsername()));
        });

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);

        if (this.userRepository.count() == 0) {
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
            user.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
            user.getAuthorities().add(this.roleService.findByAuthority("ROLE_ROOT"));
            user.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        } else {
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setProfileDetails(new ProfileDetails());
        user.setRegisteredOn(LocalDateTime.now());
        user.getProfileDetails().setImageUrl("https://res.cloudinary.com/nuxel-application/image/upload/v1617252803/no-profile-img_pqxacn.png");
        

        this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(user, RegisterViewModel.class);
    }

    @Override
    public LoginViewModel loginUser(LoginBindingModel loginBindingModel) throws UserNotFoundException {

        User user = this.modelMapper.map(findUserByUsername(loginBindingModel.getUsername()),
                User.class);

        if(user != null) {
            if (bCryptPasswordEncoder.matches(loginBindingModel.getPassword(), user.getPassword())) {
                final String token = jwtUtils.generateToken(user);

                return new LoginViewModel(token);
            }
        } else {
            throw new SecurityException("Wrong credentials!");
        }

        throw new UserNotFoundException("No such user exists!");
    }

    @Override
    public UserServiceModel findUserByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email).orElse(null);
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findUserByUsername(username)
                .orElseThrow
                        (() ->
                                new UsernameNotFoundException(String.format("%s user not found", username)));
    }
}
