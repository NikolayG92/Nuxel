package com.example.nuxel.userservice.services.impl;

import com.example.nuxel.userservice.config.jwt.JwtUtils;
import com.example.nuxel.userservice.exceptions.PasswordDoNotMatchException;
import com.example.nuxel.userservice.exceptions.UserNotFoundException;
import com.example.nuxel.userservice.model.bindingModels.LoginBindingModel;
import com.example.nuxel.userservice.model.bindingModels.ReviewBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserEditBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.model.entities.Gender;
import com.example.nuxel.userservice.model.entities.ProfileDetails;
import com.example.nuxel.userservice.model.entities.Review;
import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.model.view.LoginViewModel;
import com.example.nuxel.userservice.model.view.RegisterViewModel;
import com.example.nuxel.userservice.repositories.UserRepository;
import com.example.nuxel.userservice.services.CloudinaryService;
import com.example.nuxel.userservice.services.ReviewService;
import com.example.nuxel.userservice.services.RoleService;
import com.example.nuxel.userservice.services.UserService;
import com.example.nuxel.userservice.services.serviceModels.ProfileDetailsServiceModel;
import com.example.nuxel.userservice.services.serviceModels.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;
    private final CloudinaryService cloudinaryService;
    private final ReviewService reviewService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder,
                           JwtUtils jwtUtils, CloudinaryService cloudinaryService, ReviewService reviewService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtils = jwtUtils;
        this.cloudinaryService = cloudinaryService;
        this.reviewService = reviewService;
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
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        ProfileDetailsServiceModel profileDetailsServiceModel = this.modelMapper
                .map(user.getProfileDetails(), ProfileDetailsServiceModel.class);

        userServiceModel.setProfileDetails(profileDetailsServiceModel);
        return userServiceModel;
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
        user.getProfileDetails().setImageUrl("https://res.cloudinary.com/nuxel-application/image/upload/v1618494650/blank-profile-picture-973460_1280_l0xqhh.webp");


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
    public UserServiceModel changeProfilePicture(String name, MultipartFile file) throws IOException, UserNotFoundException {
        String imageUrl = this.cloudinaryService.uploadImageToCurrentFolder(file, "profile-pictures");
        User user = this.userRepository.findUserByUsername(name).orElseThrow(() -> new UserNotFoundException("No such user exists!"));
        user.getProfileDetails().setImageUrl(imageUrl);
        this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel changePassword(String oldPassword, String newPassword, String username) throws UserNotFoundException, PasswordDoNotMatchException {
        User user = this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("%s user not found", username)));
        if(this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())){
            user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.userRepository.saveAndFlush(user);
        }else {
            throw new PasswordDoNotMatchException("Old password is wrong!");
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel changeProfileDetails(UserEditBindingModel userEditBindingModel, String name) throws UserNotFoundException {
        User user = this.userRepository.findUserByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("No such user exists!"));
        user.getProfileDetails().setFirstName(userEditBindingModel.getFirstName());
        user.getProfileDetails().setLastName(userEditBindingModel.getLastName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(userEditBindingModel.getDateOfBirth().substring(0, 10), formatter);
        user.getProfileDetails().setDateOfBirth(localDate);
        user.getProfileDetails().setPhoneNumber(userEditBindingModel.getPhoneNumber());
        user.getProfileDetails().setGender(Gender.valueOf(userEditBindingModel.getGender()));
        this.userRepository.saveAndFlush(user);

        return this.modelMapper.
                map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserById(String id) {
        User user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public Double  rateUser(ReviewBindingModel ratingBindingModel) {
        User user = userRepository.findById(ratingBindingModel.getSellerId()).orElse(null);
        Review review = reviewService.addReview(ratingBindingModel);
        Double rating = 0.0;

        if(review != null){
            user.getProfileDetails().getReviews().add(review);
            rating = reviewService.rating(ratingBindingModel.getSellerId());
            user.getProfileDetails().setRating(rating);
            userRepository.save(user);
        }
        return rating;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findUserByUsername(username)
                .orElseThrow
                        (() -> new UsernameNotFoundException(String.format("%s user not found", username)));
    }
}
