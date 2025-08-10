package com.budgetbuddy.budgetbuddyapp.repository;

import com.budgetbuddy.budgetbuddyapp.entity.PersonalExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalExpenseRepository extends JpaRepository<PersonalExpense, Long> {
    List<PersonalExpense> findByUserId(Long userId);

    //Optional<PersonalExpense> findById(Long id);
}