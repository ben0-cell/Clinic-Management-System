package com.example.Clinic.Management.System.service;

import com.example.Clinic.Management.System.model.Roles;
import com.example.Clinic.Management.System.model.Users;
import com.example.Clinic.Management.System.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Users registerUser(Users users) {

        if (usersRepository.findByUsername(users.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        else {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            //set the default user as patient for normal registeration
            users.setRoles(Roles.PATIENT);
            return usersRepository.save(users);
        }
    }

}
