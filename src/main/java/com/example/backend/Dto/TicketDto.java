package com.example.backend.Dto;

import java.time.LocalDateTime;

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
public class TicketDto {
    
    @NotNull
    @NotEmpty(message = "concert should not be empty")
    private String concert;

    @NotNull(message = "user not be empty")
    private Long user;
    
    @NotNull
    @NotEmpty(message = "purchaseDate not be empty")
    private LocalDateTime purchaseDate;

    @NotNull(message = "quantity not be empty")
    private int quantity; 

    @NotNull
    @NotEmpty(message = "categoryname not be empty")
    private String  categoryname; 

    @NotNull(message = "totalPric not be empty")
    private int totalPrice; 

    @NotNull
    @NotEmpty(message = "status not be empty")
    private String status;
}