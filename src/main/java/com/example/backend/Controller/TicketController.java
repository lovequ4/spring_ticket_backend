package com.example.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Dto.TicketDto;
import com.example.backend.Entity.Ticket;
import com.example.backend.Services.TicketService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@Tag(name="Ticket")
@RequestMapping("/api/ticket")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

     @PostMapping("/create")
    public ResponseEntity<String>createTicket( @RequestBody TicketDto ticketDto){
        return ticketService.createTicket(ticketDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getTicketByUser(@PathVariable Long id){
        return  ticketService.getTicketByUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>editTicketByUser(@PathVariable Long id, @Valid @RequestBody Ticket ticket){
        ticket.setId(id);
        return  ticketService.editTicketByUser(ticket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>delTicketByUser(@PathVariable Long id,Ticket ticket){
        ticket.setId(id);
        return  ticketService.delTicketByUser(ticket);
    }
}
