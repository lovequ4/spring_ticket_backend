package com.example.backend.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.VenueDto;
import com.example.backend.Entity.Venue;


@Service
public interface VenueService {
    ResponseEntity<String>createVenue(VenueDto venueDto);
    ResponseEntity<?> getVenue();
    ResponseEntity<String>editVenue(Venue venue);
    ResponseEntity<String>delVenue(Venue venue);
}
