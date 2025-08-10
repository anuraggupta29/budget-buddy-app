package com.budgetbuddy.budgetbuddyapp.security;

import com.budgetbuddy.budgetbuddyapp.entity.User;
import com.budgetbuddy.budgetbuddyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    // Inject your UserRepository here
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userO = userRepository.findByUsername(username);
        if(userO.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userO.get();
        return new AppUserDetails(user.getId(), user.getUsername(), user.getPasswordHash(), Collections.emptyList());
    }

    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
        Optional<User> userO = userRepository.findById(userId);
        if(userO.isEmpty()) {
            throw new UsernameNotFoundException("Userid not found");
        }

        User user = userO.get();
        return new AppUserDetails(user.getId(), user.getUsername(), user.getPasswordHash(), Collections.emptyList());
    }


}