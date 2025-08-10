package com.budgetbuddy.budgetbuddyapp.dto.responsedto;

import com.budgetbuddy.budgetbuddyapp.entity.PersonalExpense;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class PersonalExpenseResponseRecord {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private LocalDateTime createdAt;

    public PersonalExpenseResponseRecord(PersonalExpense personalExpense){
        this.id = personalExpense.getId();
        this.description = personalExpense.getDescription();
        this.amount = personalExpense.getAmount();
        this.expenseDate = personalExpense.getExpenseDate();
        this.createdAt = personalExpense.getCreatedAt();
    }
}
