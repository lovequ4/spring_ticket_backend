package com.example.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.Dto.ConcertDto;
import com.example.backend.Entity.Concert;
import com.example.backend.Services.ConcertService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@Tag(name="Concert")
@RequestMapping("/api/concert")
public class ConcertController {
    
    private ConcertService concertService;

    public ConcertController(ConcertService concertService){
        this.concertService = concertService;
    }

    @PostMapping(value="create",consumes = "multipart/form-data")
    public ResponseEntity<String>CreateConcert( ConcertDto concertDto,@RequestParam MultipartFile concertImg){
        return concertService.CreateConcert(concertDto,concertImg);
    }

    @GetMapping("")
    public ResponseEntity<?>getConcert(){
        return concertService.getConcert();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>EditConcert(@PathVariable Long id, @Valid @RequestBody Concert concert){
        return concertService.EditConcert(concert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>DelConcert(@PathVariable Long id, @Valid @RequestBody Concert concert){
        return concertService.DelConcert(concert);
    }
}
