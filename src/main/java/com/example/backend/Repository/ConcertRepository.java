package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entity.Concert;
import java.util.Optional;


public interface ConcertRepository extends JpaRepository<Concert,Long>{
    Concert findByTitle(String title);
    Optional <Concert> findById(Long id);
    boolean existsByTitle(String title);
}
