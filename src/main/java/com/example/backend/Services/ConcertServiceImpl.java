package com.example.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.ConcertDto;
import com.example.backend.Entity.Concert;
import com.example.backend.Entity.Venue;
import com.example.backend.Repository.ConcertRepository;
import com.example.backend.Repository.VenueRepository;

@Service
public class ConcertServiceImpl implements ConcertService {
    private ConcertRepository concertRepository;
    private VenueRepository venueRepository;

    public ConcertServiceImpl(ConcertRepository concertRepository,VenueRepository venueRepository){
        this.concertRepository = concertRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public ResponseEntity<String>CreateConcert(ConcertDto concertDto){
        if(concertRepository.existsByTitle(concertDto.getTitle())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("title is already exist.");
        }

        Venue venue = venueRepository.findByVenuename(concertDto.getVenuename()); // 假設有一個 venueRepository
        if (venue == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Venue not found.");
        }

        Concert concert = new Concert();
        concert.setTitle(concertDto.getTitle());
        concert.setDescription(concertDto.getDescription());
        concert.setVenue(venue);
        concert.setArtist(concertDto.getArtist());
        concert.setDate(concertDto.getDate());
        concert.setTicketQuantity(concertDto.getTicketQuantity());

        Concert result = concertRepository.save(concert);
        if(result!=null){
            return ResponseEntity.ok("Concert create successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Concert create failed");
        }   
    }

    @Override
    public ResponseEntity<?> getConcert() {  
        List<Concert> concerts = concertRepository.findAll();
            if (concerts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Concert not found");
            }
        return ResponseEntity.ok(concerts);
    }

    @Override
    public ResponseEntity<String>EditConcert(Concert concert){
        Optional<Concert> optionalConcert = concertRepository.findById(concert.getId());
        if(optionalConcert.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Concert not found");
        }  
       
        concert.setDescription(concert.getDescription());
        concert.setVenue(concert.getVenue());
        concert.setArtist(concert.getArtist());
        concert.setDate(concert.getDate());
        concert.setTicketQuantity(concert.getTicketQuantity());

        Concert result = concertRepository.save(concert);
        if(result!=null){
            return ResponseEntity.ok("Concert edit successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Concert edit failed");
        }   
    }

    @Override
    public ResponseEntity<String>DelConcert(Concert concert){
        Optional<Concert> OptionalConcert = concertRepository.findById(concert.getId());
        if(OptionalConcert.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Concert not found");
        }  

        concertRepository.delete(OptionalConcert.get());
        
        if (!concertRepository.existsById(concert.getId())) {
            return ResponseEntity.ok("Concert deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Concert delete failed");
        }  
    }
}
