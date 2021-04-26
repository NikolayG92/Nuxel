package com.example.nuxel.userservice.services.serviceModels;

import com.example.nuxel.userservice.model.entities.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ProfileDetailsServiceModel {

    private String id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Gender gender;
}
