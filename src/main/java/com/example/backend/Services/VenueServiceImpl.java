package com.example.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.VenueDto;
import com.example.backend.Entity.Venue;
import com.example.backend.Repository.VenueRepository;

@Service
public class VenueServiceImpl implements VenueService {
    private VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }


    @Override
    public ResponseEntity<String>createVenue(VenueDto venueDto){
        if(venueRepository.existsByVenuename(venueDto.getVenuename())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("VenueName is already exist.");
        }

        Venue venue = new Venue();
        venue.setVenuename(venueDto.getVenuename());
        venue.setLocation(venueDto.getLocation());
        venue.setCapacity(venueDto.getCapacity());

        Venue result = venueRepository.save(venue);

        if(result!=null){
            return ResponseEntity.ok("Venue create successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue create failed");
        }   
    }

    @Override
    public ResponseEntity<?> getVenue() {  
    List<Venue> venues = venueRepository.findAll();
        if (venues.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue not found");
        }
    return ResponseEntity.ok(venues);
    }

    @Override
    public ResponseEntity<String>editVenue(Venue venue){
        Optional<Venue> optionalVenue = venueRepository.findById(venue.getId());
        if(optionalVenue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue not found");
        }  
       
        venue.setVenuename(venue.getVenuename());
        venue.setLocation(venue.getLocation());
        venue.setCapacity(venue.getCapacity());

        Venue result = venueRepository.save(venue);
        if(result!=null){
            return ResponseEntity.ok("Venue edit successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue edit failed");
        }   
    }

    @Override
    public ResponseEntity<String>delVenue(Venue venue){
        Optional<Venue> OptionalConcert = venueRepository.findById(venue.getId());
        if(OptionalConcert.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue not found");
        }  

        venueRepository.delete(OptionalConcert.get());
        
        if (!venueRepository.existsById(venue.getId())) {
            return ResponseEntity.ok("Venue deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue delete failed");
        }  
    }
}        