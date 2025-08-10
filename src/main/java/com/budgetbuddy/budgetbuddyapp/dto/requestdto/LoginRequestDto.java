package com.budgetbuddy.budgetbuddyapp.dto.requestdto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LoginRequestDto {
    private String username;
    private String password;
}
