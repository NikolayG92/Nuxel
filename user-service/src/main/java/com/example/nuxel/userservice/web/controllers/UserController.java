package com.example.nuxel.userservice.web.controllers;

import com.example.nuxel.userservice.anotations.PageTitle;
import com.example.nuxel.userservice.exceptions.UserNotFoundException;
import com.example.nuxel.userservice.model.bindingModels.LoginBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.model.view.LoginViewModel;
import com.example.nuxel.userservice.model.view.RegisterViewModel;
import com.example.nuxel.userservice.services.UserService;
import com.example.nuxel.userservice.services.serviceModels.UserServiceModel;
import com.example.nuxel.userservice.validators.UserRegisterValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<UserServiceModel>> getAllUsers(){

        return ResponseEntity
                .ok()
                .body(this.userService.getAllUsers());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserServiceModel> getCurrentUser(Principal principal){

        return ResponseEntity
                .ok()
                .body(this.userService.findUserByUsername(principal.getName()));
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterViewModel> registerUser(@Valid @RequestBody UserRegisterBindingModel userRegisterBindingModel) {
        RegisterViewModel registerViewModel =
                this.userService.registerUser(userRegisterBindingModel);

        return ResponseEntity
                .ok()
                .body(registerViewModel);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginViewModel> login(@Valid @RequestBody LoginBindingModel loginBindingModel ) throws UserNotFoundException {
        this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginBindingModel.getUsername(),
                        loginBindingModel.getPassword()));
        return ResponseEntity.ok(
                this.userService.loginUser(loginBindingModel)
        );

    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logoutUser(HttpSession httpSession){

        httpSession.invalidate();

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<UserServiceModel> getUserByEmail(@PathVariable("email") String email){

        return ResponseEntity
                .ok(this.userService.findUserByEmail(email));
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<UserServiceModel> getUserByUsername(@PathVariable("username") String username){

        return ResponseEntity
                .ok(this.userService.findUserByUsername(username));
    }


}