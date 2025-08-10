package com.budgetbuddy.budgetbuddyapp.service;

import com.budgetbuddy.budgetbuddyapp.dto.responsedto.PersonalExpenseResponseRecord;
import com.budgetbuddy.budgetbuddyapp.entity.PersonalExpense;
import com.budgetbuddy.budgetbuddyapp.entity.User;
import com.budgetbuddy.budgetbuddyapp.dto.requestdto.PersonalExpenseDto;
import com.budgetbuddy.budgetbuddyapp.repository.PersonalExpenseRepository;
import com.budgetbuddy.budgetbuddyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonalExpenseService {

    @Autowired
    private PersonalExpenseRepository personalExpenseRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> savePersonalExpense(PersonalExpenseDto personalExpenseDto, Long userId){

        User user = userRepository.findById(userId).get();
        PersonalExpense personalExpense = new PersonalExpense(personalExpenseDto, user);
        personalExpenseRepository.save(personalExpense);

        Map<String, String> response = new HashMap<>();
        response.put("message", "expense saved");
        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getPersonalExpenses(Long userId){

        List<PersonalExpenseResponseRecord> expenses = personalExpenseRepository.findByUserId(userId)
                .stream()
                .map(pe -> new PersonalExpenseResponseRecord(pe))
                .toList();

        Map<String, List<PersonalExpenseResponseRecord>> response = new HashMap<>();
        response.put("expenses", expenses);
        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> deletePersonalExpense(Long expenseId, Long userId) throws Exception{
        Optional<PersonalExpense> pe = personalExpenseRepository.findById(expenseId);

        if(pe.isEmpty()) throw new Exception("expense not found");
        if (!pe.get().getUser().getId().equals(userId)) throw new Exception("expense not yours");

        personalExpenseRepository.deleteById(expenseId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "expense delete successfully");
        System.out.println(response);

        return ResponseEntity.ok(response);
    }
}
