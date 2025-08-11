package com.budgetbuddy.budgetbuddyapp.controller;

import com.budgetbuddy.budgetbuddyapp.dto.requestdto.SignupRequestDto;
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
public class SignupController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/signup")
    @Operation(security = {})
    public ResponseEntity<?> signupUser(@RequestBody SignupRequestDto signupRequestDto){
        System.out.println("Signup Request Received");

        try {
            return userAuthService.registerUser(signupRequestDto);
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("An error occurred");

            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error, please try again");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
