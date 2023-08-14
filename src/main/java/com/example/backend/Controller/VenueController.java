package com.example.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Dto.VenueDto;
import com.example.backend.Entity.Venue;
import com.example.backend.Services.VenueService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@Tag(name="Venue")
@RequestMapping("/api/venue")
public class VenueController {
    
    private VenueService venueService;

    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }

    @PostMapping("/create")
    public ResponseEntity<String>createVenue( @RequestBody VenueDto venueDto){
        return venueService.createVenue(venueDto);
    }

    @GetMapping("/get")
    public ResponseEntity<?>getVenue(){
        return  venueService.getVenue();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>editVenue(@PathVariable Long id, @Valid @RequestBody Venue venue){
        venue.setId(id);
        return  venueService.editVenue(venue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>delVenue(@PathVariable Long id, @Valid @RequestBody Venue venue){
        venue.setId(id);
        return  venueService.delVenue(venue);
    }
}
