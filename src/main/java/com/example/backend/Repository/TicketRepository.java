package com.example.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long>{
    List<Ticket> findByUserId(Long userId);
}
