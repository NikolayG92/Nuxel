package com.example.nuxel.userservice.validators;


import com.example.nuxel.userservice.anotations.Validator;
import com.example.nuxel.userservice.model.bindingModels.UserEditBindingModel;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.repositories.UserRepository;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static com.example.nuxel.userservice.constants.UserConstants.*;
import static com.example.nuxel.userservice.constants.UserConstants.EMAIL_ALREADY_EXIST;

@Validator
public class UserEditValidator implements org.springframework.validation.Validator {
    private final UserRepository userRepository;

    public UserEditValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserEditBindingModel userEditBindingModel = (UserEditBindingModel) o;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(userEditBindingModel.getDateOfBirth().substring(0, 10), formatter);
        if(localDate.isAfter(LocalDate.now())){
            errors.rejectValue("dateOfBirth", DATE_IS_NOT_VALID,
                    DATE_IS_NOT_VALID);
        }

    }
}
