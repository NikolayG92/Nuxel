package com.example.nuxel.userservice.web.controllers;

import com.example.nuxel.userservice.anotations.PageTitle;
import com.example.nuxel.userservice.model.bindingModels.UserRegisterBindingModel;
import com.example.nuxel.userservice.services.UserService;
import com.example.nuxel.userservice.services.serviceModels.UserServiceModel;
import com.example.nuxel.userservice.validators.UserRegisterValidator;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;

    public UserController(UserService userService, ModelMapper modelMapper, UserRegisterValidator userRegisterValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = userRegisterValidator;
    }

    @GetMapping("/register")
    @PageTitle("Register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(@ModelAttribute(name = "userRegisterBindingModel") UserRegisterBindingModel model, ModelAndView modelAndView) {
        modelAndView.addObject("userRegisterBindingModel", model);
        return super.view("register");
    }

    @PostMapping("/register")
    @PageTitle("Register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@Valid @ModelAttribute(name = "userRegisterBindingModel") UserRegisterBindingModel model
            , BindingResult bindingResult, ModelAndView modelAndView) {
        userRegisterValidator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("userRegisterBindingModel", model);
            return super.view("register");
        }

        this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));

        return super.redirect("/users/login");
    }

    @GetMapping("/login")
    @PageTitle("Login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("login");
    }

}
