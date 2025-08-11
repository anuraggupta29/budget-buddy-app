package com.budgetbuddy.budgetbuddyapp.controller;

import com.budgetbuddy.budgetbuddyapp.dto.requestdto.LoginRequestDto;
import com.budgetbuddy.budgetbuddyapp.service.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/login")
    @Operation(security = {})
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequestDto){

        System.out.println("Login Request Received");

        try {
            return userAuthService.loginUser(loginRequestDto);

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Invalid username / password");

            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
