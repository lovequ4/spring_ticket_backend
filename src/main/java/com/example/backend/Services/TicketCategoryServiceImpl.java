package com.example.backend.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.TicketCategoryDto;
import com.example.backend.Entity.TicketCategory;
import com.example.backend.Repository.TicketCategoryRepository;

@Service
public class TicketCategoryServiceImpl implements TicketCategoryService {
    private TicketCategoryRepository ticketCategoryRepository;

    public TicketCategoryServiceImpl(TicketCategoryRepository ticketCategoryRepository){
        this.ticketCategoryRepository = ticketCategoryRepository;
    }

    @Override
    public ResponseEntity<String>createTicketCategory(TicketCategoryDto ticketCategoryDto){
        if(ticketCategoryRepository.existsByCategoryName(ticketCategoryDto.getCategoryName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TicketCategoryName is already exist.");
        }

        TicketCategory ticketCategory = new TicketCategory();
        ticketCategory.setCategoryName(ticketCategoryDto.getCategoryName());
        ticketCategory.setPrice(ticketCategoryDto.getPrice());
    

        TicketCategory result = ticketCategoryRepository.save(ticketCategory);

        if(result!=null){
            return ResponseEntity.ok("TicketCategory create successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TicketCategory create failed");
        }   
    }

    @Override
    public ResponseEntity<?> getTicketCategory() {  
    List<TicketCategory> ticketCategorys = ticketCategoryRepository.findAll();
        if (ticketCategorys.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TicketCategory not found");
        }
    return ResponseEntity.ok(ticketCategorys);
    }

    @Override
    public ResponseEntity<String>editTicketCategory(TicketCategory ticketCategory){
        Optional<TicketCategory> optionalVenue = ticketCategoryRepository.findById(ticketCategory.getId());
        if(optionalVenue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TicketCategory not found");
        }  
       
        ticketCategory.setCategoryName(ticketCategory.getCategoryName());
        ticketCategory.setPrice(ticketCategory.getPrice());

        TicketCategory result = ticketCategoryRepository.save(ticketCategory);
        if(result!=null){
            return ResponseEntity.ok("TicketCategory edit successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TicketCategory edit failed");
        }   
    }

    @Override
    public ResponseEntity<String>delTicketCategory(TicketCategory ticketCategory){
        Optional<TicketCategory> OptionalConcert = ticketCategoryRepository.findById(ticketCategory.getId());
        if(OptionalConcert.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TicketCategory not found");
        }  

        ticketCategoryRepository.delete(OptionalConcert.get());
        
        if (!ticketCategoryRepository.existsById(ticketCategory.getId())) {
            return ResponseEntity.ok("TicketCategory deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TicketCategory delete failed");
        }  
    }


}
