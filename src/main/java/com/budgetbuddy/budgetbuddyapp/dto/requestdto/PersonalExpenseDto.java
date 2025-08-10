package com.budgetbuddy.budgetbuddyapp.dto.requestdto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonalExpenseDto {
    private String expenseDescription;
    private Double expenseAmount;
    private LocalDate expenseDate;

    @Override
    public String toString() {
        return "{" +
                ", expenseDescription='" + expenseDescription + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", expenseDate=" + expenseDate +
                '}';
    }
}
