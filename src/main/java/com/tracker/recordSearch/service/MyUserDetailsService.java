//package com.tracker.recordSearch.service;
//
//import com.tracker.recordSearch.domain.User;
//import com.tracker.recordSearch.domain.UserPrincipal;
//import com.tracker.recordSearch.repository.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.security.Principal;
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
//        if  (user == null) {
//            throw new UsernameNotFoundException("User not found!");
//        }
//        return new UserPrincipal(user);
//    }
//}
