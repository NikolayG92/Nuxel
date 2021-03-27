package com.example.nuxel.userservice.model.bindingModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
@Setter
public class UserRegisterBindingModel {

    private String username;
    @Email
    private String email;
    private String password;
    private String confirmPassword;
}
