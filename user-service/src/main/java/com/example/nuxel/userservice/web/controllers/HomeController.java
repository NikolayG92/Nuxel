package com.example.nuxel.userservice.web.controllers;

import com.example.nuxel.userservice.anotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    @GetMapping("/")
    @PageTitle("Welcome")
    public ModelAndView index(ModelAndView modelAndView) {
        return view("index", modelAndView);

    }

}
