package com.example.backend.Dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

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
public class ConcertDto
{   
    @NotNull
    @NotEmpty(message = "Venuename should not be empty")
    private String  venuename;

    @NotNull
    @NotEmpty(message = "title should not be empty")
    private String title;
    
    @NotNull
    @NotEmpty(message = "description should not be empty")
    private String description ;

    @NotNull
    @NotEmpty(message = "artist should not be empty")
    private String artist ;

    @NotNull(message = "date not be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    @NotNull(message = "ticketQuantity not be empty")
    private int ticketQuantity;

}