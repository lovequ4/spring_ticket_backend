package com.example.backend.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; 

    @Column(nullable = false)
    private LocalDateTime purchaseDate;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private TicketCategory category;

    @Column(nullable = false)
    private int quantity; 

    @Column(nullable = false)
    private int totalPrice; 
    
    @Column(nullable = false)
    private TicketStatus status; 

    public enum TicketStatus {
        PENDING_PAYMENT,
        ALREADY_PAYMENT,
        USED,
        CANCELLED,
    }
    
}

