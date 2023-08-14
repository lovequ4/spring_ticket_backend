package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entity.TicketCategory;

public interface TicketCategoryRepository  extends JpaRepository<TicketCategory,Long> {
    TicketCategory  findByCategoryName(String categoryName);
    boolean existsByCategoryName(String categoryName);
}
