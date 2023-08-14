package com.example.backend.Dto;

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
public class TicketCategoryDto {
    
    @NotNull
    @NotEmpty(message = "categoryName should not be empty")
    private String categoryName;

    @NotNull(message = "capacity not be empty")
    private int price;
}