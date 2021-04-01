package com.example.nuxel.userservice.model.bindingModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginBindingModel {

    private String username;
    private String password;
}
