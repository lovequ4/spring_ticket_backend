package com.example.backend.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.TicketCategoryDto;
import com.example.backend.Entity.TicketCategory;


@Service
public interface TicketCategoryService{
    ResponseEntity<String>createTicketCategory(TicketCategoryDto ticketCategoryDto);
    ResponseEntity<?> getTicketCategory();
    ResponseEntity<String>editTicketCategory(TicketCategory ticketCategory);
    ResponseEntity<String>delTicketCategory(TicketCategory ticketCategory);
}
