package com.example.accounts.service;

import com.example.accounts.model.User;
import com.example.accounts.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User repoUser = userRepo.findUserByUserName(username);
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(repoUser.getUsername(),repoUser.getPassword(), new ArrayList<>());
        return user;
    }
}
