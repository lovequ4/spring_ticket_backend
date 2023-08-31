package com.example.backend.Services;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.backend.Entity.Ticket;
import com.example.backend.Entity.User;
import com.example.backend.Repository.TicketRepository;
import com.example.backend.Repository.UserRepository;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class CheckOutServiceImpl implements CheckOutService {
    private TicketRepository ticketRepository;
	private UserRepository userRepository;

    public CheckOutServiceImpl (TicketRepository ticketRepository,UserRepository userRepository){
        this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
    }

	
    @Override
    public String ecpayCheckout(Long userId,Long ticketId) {
		User user = userRepository.findById(userId).orElse(null);
		Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
		if(user==null && ticket==null){
			return null;
		}

		LocalDateTime purchaseDateTime = ticket.getPurchaseDate();

		// Create a DateTimeFormatter for the desired output format
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		// Format the LocalDateTime object as a string
		String formattedDateTime = purchaseDateTime.format(outputFormatter);


		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		
		AllInOne all = new AllInOne("");
		
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(uuId); 
        obj.setMerchantTradeDate(formattedDateTime);
        obj.setTotalAmount(String.valueOf(ticket.getTotalPrice()));
        obj.setTradeDesc("Ticket Purchase: " + ticket.getConcert().getTitle());
        obj.setItemName(ticket.getConcert().getTitle());
        obj.setReturnURL("http://211.23.128.214:5000"); 
        obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		
		return form;
	}
}
