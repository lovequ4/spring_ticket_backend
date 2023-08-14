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
public class VenueDto {
    
    @NotNull
    @NotEmpty(message = "venueName should not be empty")
    private String venuename;

    @NotNull
    @NotEmpty(message = "location should not be empty")
    private String location;

    @NotNull
    @NotEmpty(message = "capacity not be empty")
    private int capacity;
}
