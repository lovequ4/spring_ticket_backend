package com.example.backend.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long Id;

    @Column(nullable = false)
    private String venuename;
    
    @Column(nullable = false)
    private String location;
    
    @Column(nullable = false)
    private int capacity;
}
