package com.example.nuxel.userservice.validators;

import com.example.nuxel.userservice.anotations.Validator;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.repositories.UserRepository;
import org.springframework.validation.Errors;

import java.util.regex.Pattern;

import static com.example.nuxel.userservice.constants.UserConstants.*;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {
    private final UserRepository userRepository;

    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) o;

        if(userRegisterBindingModel.getUsername() == null){
            errors.rejectValue("username", USERNAME_CANNOT_BE_EMPTY,
                    USERNAME_CANNOT_BE_EMPTY);
        }else {
            if(this.userRepository.findUserByUsername(userRegisterBindingModel.getUsername()).isPresent()){
                errors.rejectValue("username", USERNAME_ALREADY_EXIST,
                        USERNAME_ALREADY_EXIST);
            }
            if(userRegisterBindingModel.getUsername().length() < 3){
                errors.rejectValue("username", USERNAME_LENGTH_IS_NOT_VALID,
                        USERNAME_LENGTH_IS_NOT_VALID);
            }
            if(userRegisterBindingModel.getUsername().contains(" ")){
                errors.rejectValue("username", USERNAME_IS_NOT_VALID,
                        USERNAME_IS_NOT_VALID);
            }
        }
        if(userRegisterBindingModel.getPassword() == null){
            errors.rejectValue("password", PASSWORD_CANNOT_BE_EMPTY,
                    PASSWORD_CANNOT_BE_EMPTY);
        }else {
            Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-_]).{8,}$");
            if(!pattern.matcher(userRegisterBindingModel.getPassword()).matches()){
                errors.rejectValue("password", PASSWORD_NOT_VALID,
                        PASSWORD_NOT_VALID);
            }
        }
        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            errors.rejectValue("password", PASSWORDS_DO_NOT_MATCH,
                    PASSWORDS_DO_NOT_MATCH);
        }
        if(this.userRepository.findUserByEmail(userRegisterBindingModel.getEmail()).isPresent()){
            errors.rejectValue("email", EMAIL_ALREADY_EXIST,
                    EMAIL_ALREADY_EXIST);
        }
    }
}
