package com.example.backend.Services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.LoginDto;
import com.example.backend.Dto.SignupDto;

@Service
public interface UserService {
    ResponseEntity<String> signup(SignupDto signupDto);
    ResponseEntity<Map<String, Object>>  login (LoginDto loginDto);
}
