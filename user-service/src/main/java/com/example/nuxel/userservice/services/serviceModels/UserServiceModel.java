package com.example.nuxel.userservice.services.serviceModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private Set<RoleServiceModel> authorities;
}
