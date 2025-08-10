package com.budgetbuddy.budgetbuddyapp.service;

import com.budgetbuddy.budgetbuddyapp.entity.User;
import com.budgetbuddy.budgetbuddyapp.dto.requestdto.LoginRequestDto;
import com.budgetbuddy.budgetbuddyapp.dto.requestdto.SignupRequestDto;
import com.budgetbuddy.budgetbuddyapp.repository.UserRepository;
import com.budgetbuddy.budgetbuddyapp.security.AppUserDetails;
import com.budgetbuddy.budgetbuddyapp.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserAuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<?> registerUser(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String email = signupRequestDto.getEmail();
        String rawPassword = signupRequestDto.getPassword();

        String hashed = passwordEncoder.encode(rawPassword);
        User user = new User(username, email, hashed);

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Sign up successful");
        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> loginUser(LoginRequestDto loginRequestDto){
        // Authenticate user credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        // Generate JWT token
        String token = jwtService.generateToken(userId);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("redirect", "/dashboard");
        System.out.println(response);

        return ResponseEntity.ok(response);
    }
}
