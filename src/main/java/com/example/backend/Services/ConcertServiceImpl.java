package com.example.backend.Services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.Dto.ConcertDto;
import com.example.backend.Entity.Concert;
import com.example.backend.Entity.Venue;
import com.example.backend.Repository.ConcertRepository;
import com.example.backend.Repository.VenueRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ConcertServiceImpl implements ConcertService {
    private ConcertRepository concertRepository;
    private VenueRepository venueRepository;
    private HttpServletRequest httpServletRequest;

    public ConcertServiceImpl(ConcertRepository concertRepository,
                              VenueRepository venueRepository,
                              HttpServletRequest httpServletRequest){
        this.concertRepository = concertRepository;
        this.venueRepository = venueRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public ResponseEntity<String>CreateConcert(ConcertDto concertDto,MultipartFile concertImg){
        try {
            String fileName = StringUtils.cleanPath(concertImg.getOriginalFilename());
            Path imagePath = Paths.get("concertImg", fileName);
            Files.copy(concertImg.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
    

            if(concertRepository.existsByTitle(concertDto.getTitle())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title is already exist.");
            }

            Venue venue = venueRepository.findByVenuename(concertDto.getVenuename());
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
            concert.setConcertImg(imagePath.toString());
            
            Concert result = concertRepository.save(concert);
            if(result!=null){
                return ResponseEntity.ok("Concert create successfully.");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Concert create failed");
            }   
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process request.");
        }
    }


    public String getProductImageUrl(String imageName) {
        return "http://" + httpServletRequest.getServerName()+":"+ httpServletRequest.getServerPort() + "/" + imageName;
    }

    
    @Override
    public ResponseEntity<?> getConcert() {  
        List<Concert> concerts = concertRepository.findAll();
        if (concerts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Concert not found");
        }
        for(Concert concert : concerts){
            concert.setConcertImg(getProductImageUrl(concert.getConcertImg()));
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
