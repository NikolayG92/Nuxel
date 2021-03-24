package com.example.nuxel.userservice.controllers;

import com.example.nuxel.userservice.model.entities.User;
import com.example.nuxel.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){

        return ResponseEntity.ok(userService.findById(id));
    }

}
