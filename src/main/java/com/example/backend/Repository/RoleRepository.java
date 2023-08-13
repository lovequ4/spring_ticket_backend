package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
