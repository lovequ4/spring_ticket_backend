package com.example.backend.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.TicketDto;
import com.example.backend.Entity.Concert;
import com.example.backend.Entity.Ticket;
import com.example.backend.Entity.Ticket.TicketStatus;
import com.example.backend.Entity.TicketCategory;
import com.example.backend.Entity.User;
import com.example.backend.Repository.TicketRepository;
import com.example.backend.Repository.UserRepository;
import com.example.backend.Repository.ConcertRepository;
import com.example.backend.Repository.TicketCategoryRepository;

@Service
public class TicketServiceImpl implements TicketService {
    
    private TicketRepository ticketRepository;
    private ConcertRepository concertRepository;
    private UserRepository userRepository;
    private TicketCategoryRepository ticketcategoryRepository;

    public TicketServiceImpl (TicketRepository  ticketRepository, ConcertRepository concertRepository, UserRepository userRepository,TicketCategoryRepository ticketcategoryRepository){
        this.ticketRepository = ticketRepository;
        this.concertRepository = concertRepository;
        this.userRepository = userRepository;
        this.ticketcategoryRepository = ticketcategoryRepository;
    }

    @Override
    public ResponseEntity<String>createTicket(TicketDto ticketDto){
     
        Concert concert = concertRepository.findByTitle(ticketDto.getConcert()); 
        if (concert == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Concert not found.");
        }

        // Long userId = (ticketDto.getUser());
        User user = userRepository.findById(ticketDto.getUser()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        TicketCategory ticketcategory = ticketcategoryRepository.findByCategoryName(ticketDto.getCategoryname());
        if (ticketcategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TicketCategory not found.");
        }

        int ticketPrice = ticketcategory.getPrice(); 
        int totalPrice = ticketPrice * ticketDto.getQuantity();
   
   

        Ticket ticket = new Ticket();
        ticket.setConcert(concert);
        ticket.setUser(user);
        ticket.setPurchaseDate(LocalDateTime.now());
        ticket.setCategory(ticketcategory);
        ticket.setQuantity(ticketDto.getQuantity());
        ticket.setTotalPrice(totalPrice);
        ticket.setStatus(TicketStatus.PENDING_PAYMENT);
        Ticket result = ticketRepository.save(ticket);

        if(result!=null){
            return ResponseEntity.ok("Ticket create successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket create failed");
        }   
    }

    @Override
    public ResponseEntity<?> getTicketByUser(Long userId) {  
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        if (tickets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        }

            // Create a new list to hold the simplified ticket objects
        List<Map<String, Object>> response = new ArrayList<>();

        for (Ticket ticket : tickets) {
            Map<String, Object> simplifiedTicket = new HashMap<>();
            
            simplifiedTicket.put("id", ticket.getId());
            
            // Simplify venue information
            simplifiedTicket.put("venue", ticket.getConcert().getVenue().getVenuename());
            
            // Simplify concert information
            Concert concert = ticket.getConcert();
            simplifiedTicket.put("title", concert.getTitle());
            simplifiedTicket.put("artist", concert.getArtist());
            
            // Simplify category information
            TicketCategory category = ticket.getCategory();
            Map<String, Object> simplifiedCategory = new HashMap<>();
            simplifiedCategory.put("name", category.getCategoryName());
            simplifiedCategory.put("price", category.getPrice());
            simplifiedTicket.put("category", simplifiedCategory);
            
            simplifiedTicket.put("purchaseDate", ticket.getPurchaseDate());
            simplifiedTicket.put("quantity", ticket.getQuantity());
            simplifiedTicket.put("totalPrice", ticket.getTotalPrice());
            simplifiedTicket.put("status", ticket.getStatus());
            
            response.add(simplifiedTicket);
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String>editTicketByUser(Ticket ticket){
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticket.getId());
        if(optionalTicket.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        } 
        
        Ticket existingTicket = optionalTicket.get();

        existingTicket.setQuantity(ticket.getQuantity());
        existingTicket.setCategory(ticket.getCategory());
        
        int ticketPrice = existingTicket.getCategory().getPrice(); 
        int totalPrice = ticketPrice * ticket.getQuantity();
        existingTicket.setTotalPrice(totalPrice);

       Ticket result = ticketRepository.save(existingTicket);
        if(result!=null){
            return ResponseEntity.ok("Ticket edit successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket edit failed");
        }   
    }

    @Override
    public ResponseEntity<String>delTicketByUser(Ticket ticket){
        Optional<Ticket> OptionalConcert = ticketRepository.findById(ticket.getId());
        if(OptionalConcert.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        }  

        ticketRepository.delete(OptionalConcert.get());
        
        if (!ticketRepository.existsById(ticket.getId())) {
            return ResponseEntity.ok("Ticket deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket delete failed");
        }  
    }
}
