package com.example.nuxel.userservice.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class LoginViewModel {

    @NotNull
    private String jwt;
}
