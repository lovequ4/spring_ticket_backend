package com.example.backend.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.TicketDto;
import com.example.backend.Entity.Ticket;

@Service
public interface TicketService {
    ResponseEntity<String>createTicket(TicketDto tickerDto);
    ResponseEntity<?> getTicketByUser(Long Id);
    ResponseEntity<String>editTicketByUser(Ticket ticket);
    ResponseEntity<String>delTicketByUser(Ticket ticket);
}
