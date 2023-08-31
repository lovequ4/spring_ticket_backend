package com.example.backend.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="concerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int ticketQuantity;

    @Column(nullable = false)
    private String concertImg;
}
