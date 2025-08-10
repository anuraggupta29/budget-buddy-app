package com.budgetbuddy.budgetbuddyapp.controller;// FormController.java
import com.budgetbuddy.budgetbuddyapp.dto.requestdto.DeleteExpenseDto;
import com.budgetbuddy.budgetbuddyapp.dto.requestdto.PersonalExpenseDto;
import com.budgetbuddy.budgetbuddyapp.security.AppUserDetails;
import com.budgetbuddy.budgetbuddyapp.service.PersonalExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonalExpenseApiController {

    @Autowired
    private PersonalExpenseService personalExpenseService;

    @PostMapping("/send-personal-expense")
    public ResponseEntity<?> handlePersonalExpense(@RequestBody PersonalExpenseDto personalExpenseDto,
                                                   @AuthenticationPrincipal AppUserDetails user) {

        System.out.println("Personal Expense Received from user : " + user.getUsername() + " id : " + user.getUserId());

        try{
            return personalExpenseService.savePersonalExpense(personalExpenseDto, user.getUserId());
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Exception occurred");

            Map<String, String> error = new HashMap<>();
            error.put("error", "An error occurred, please try again");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }


    @GetMapping("/get-personal-expenses")
    public ResponseEntity<?> getPersonalExpensesByUsername(@AuthenticationPrincipal AppUserDetails user){

        System.out.println("Get Expense Request Received");

        try{
            return personalExpenseService.getPersonalExpenses(user.getUserId());
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Exception occurred");

            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/delete-expense")
    public ResponseEntity<?> deletePersonalExpense(@RequestBody DeleteExpenseDto deleteExpenseDto,
                                                   @AuthenticationPrincipal AppUserDetails user){
        System.out.println("Delete Expense Request Received");

        try{
            return personalExpenseService.deletePersonalExpense(deleteExpenseDto.getExpenseId(), user.getUserId());
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Exception occurred");

            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}