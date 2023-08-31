package com.example.backend.Services;

import org.springframework.stereotype.Service;

@Service
public interface CheckOutService {
    String ecpayCheckout(Long userId,Long ticketId);
}
