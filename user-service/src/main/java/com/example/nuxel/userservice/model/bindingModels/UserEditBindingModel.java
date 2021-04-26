package com.example.nuxel.userservice.model.bindingModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserEditBindingModel {

    private String imageUrl;
    private String firstName;
    private String lastName;
    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;
    private String dateOfBirth;
    private String phoneNumber;
    private String gender;
}
