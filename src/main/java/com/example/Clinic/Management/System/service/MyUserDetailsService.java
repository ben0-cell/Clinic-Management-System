package com.example.Clinic.Management.System.service;

import com.example.Clinic.Management.System.model.MyUserPrincipal;
import com.example.Clinic.Management.System.model.Users;
import com.example.Clinic.Management.System.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users =usersRepository.findByUsername(username);
        if(users.isEmpty()){
            throw new UsernameNotFoundException("user not found" +username);
        }
        return new MyUserPrincipal(users.orElse(null));
    }
}
