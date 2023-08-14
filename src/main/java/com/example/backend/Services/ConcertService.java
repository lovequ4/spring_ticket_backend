package com.example.backend.Services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.ConcertDto;
import com.example.backend.Entity.Concert;


@Service
public interface ConcertService {
    ResponseEntity<String>CreateConcert(ConcertDto concertDto);
    ResponseEntity<?> getConcert();
    ResponseEntity<String>EditConcert(Concert concert);
    ResponseEntity<String>DelConcert(Concert concert);
}
