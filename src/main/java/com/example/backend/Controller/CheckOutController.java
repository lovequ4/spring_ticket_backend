package com.example.backend.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Services.CheckOutService;

import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin
@RestController
@Tag(name="checkout")
@RequestMapping("/api/checkout")
public class CheckOutController {
    private CheckOutService checkOutService;

    public CheckOutController(CheckOutService checkOutService){
        this.checkOutService = checkOutService;
    }

    @PostMapping("/{userId}")
	public String ecpayCheckout(@PathVariable Long userId,@RequestParam Long ticketId) {
		String aioCheckOutALLForm = checkOutService.ecpayCheckout(userId,ticketId);
		
		return aioCheckOutALLForm;
	}

    
}
