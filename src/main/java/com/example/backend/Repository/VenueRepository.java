package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entity.Venue;

public interface VenueRepository extends JpaRepository<Venue,Long>{
    Venue findByVenuename(String venuename);
    boolean existsByVenuename(String venuename); 
}
