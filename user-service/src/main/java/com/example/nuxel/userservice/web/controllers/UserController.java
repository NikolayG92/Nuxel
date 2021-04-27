package com.example.nuxel.userservice.web.controllers;

import com.example.nuxel.userservice.exceptions.PasswordDoNotMatchException;
import com.example.nuxel.userservice.exceptions.UserNotFoundException;
import com.example.nuxel.userservice.model.bindingModels.LoginBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserEditBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.model.view.LoginViewModel;
import com.example.nuxel.userservice.model.view.RegisterViewModel;
import com.example.nuxel.userservice.services.UserService;
import com.example.nuxel.userservice.services.serviceModels.UserServiceModel;
import com.example.nuxel.userservice.validators.UserEditValidator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserEditValidator userEditValidator;

    public UserController(UserService userService, AuthenticationManager authenticationManager, UserEditValidator userEditValidator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userEditValidator = userEditValidator;
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

    @PostMapping(value = "/changeProfilePicture",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserServiceModel> changeProfilePicture(@RequestPart("file") @Valid MultipartFile file,
                                                Principal principal) throws UserNotFoundException, IOException {

        return ResponseEntity
                .ok()
                .body(this.userService.changeProfilePicture(principal.getName(), file));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<UserServiceModel> changePassword(@Valid @RequestBody UserEditBindingModel userEditBindingModel,
                                                           Principal principal, BindingResult result) throws UserNotFoundException, PasswordDoNotMatchException {
        return ResponseEntity.ok()
                .body(this.userService.changePassword(userEditBindingModel.getOldPassword(),
                        userEditBindingModel.getNewPassword(), principal.getName()));
    }

    @PostMapping("/changeProfileDetails")
    public ResponseEntity<UserServiceModel> changeProfileDetails(@Valid @RequestBody UserEditBindingModel userEditBindingModel,
                                                           Principal principal) throws UserNotFoundException {

        return ResponseEntity.ok()
                .body(this.userService.changeProfileDetails(userEditBindingModel, principal.getName()));
    }

}