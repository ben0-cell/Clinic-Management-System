package com.example.Clinic.Management.System.controller;

import com.example.Clinic.Management.System.model.Users;
import com.example.Clinic.Management.System.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users users){
        return authService.registerUser(users);
    }
}
