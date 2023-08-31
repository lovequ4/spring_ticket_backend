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
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Dto.TicketCategoryDto;
import com.example.backend.Entity.TicketCategory;
import com.example.backend.Services.TicketCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@Tag(name="TicketCategory")
@RequestMapping("/api/ticketcategory")
public class TicketCategoryController {

    private TicketCategoryService ticketCategoryService;

    public TicketCategoryController(TicketCategoryService ticketCategoryService){
        this.ticketCategoryService = ticketCategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String>createTicketCategory( @RequestBody TicketCategoryDto ticketCategoryDto){
        return  ticketCategoryService.createTicketCategory(ticketCategoryDto);
    }

    @GetMapping("/get")
    public ResponseEntity<?>getTicketCategory(){
        return  ticketCategoryService.getTicketCategory();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>editTicketCategory(@PathVariable Long id, @Valid @RequestBody TicketCategory ticketCategory){
        ticketCategory.setId(id);
        return  ticketCategoryService.editTicketCategory(ticketCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>delTicketCategory(@PathVariable Long id, @Valid @RequestBody TicketCategory ticketCategory){
        ticketCategory.setId(id);
        return  ticketCategoryService.delTicketCategory(ticketCategory);
    }

}
