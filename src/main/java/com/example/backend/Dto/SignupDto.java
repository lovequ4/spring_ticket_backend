package com.example.backend.Dto;

import org.springframework.stereotype.Service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class SignupDto
{   
    @NotNull
    @NotEmpty(message = "Username should not be empty")
    private String username;

    @NotNull
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotNull
    @NotEmpty(message = "Password should not be empty")
    private String password;
}