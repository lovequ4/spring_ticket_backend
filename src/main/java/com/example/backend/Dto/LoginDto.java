package com.example.backend.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
 
    @NotEmpty(message = "UserName or Email should not be empty")
    private String usernameorEmail;

    @NotEmpty(message = "Password should not be empty")
    private String password;

}
