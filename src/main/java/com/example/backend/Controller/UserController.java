package com.example.backend.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Dto.LoginDto;
import com.example.backend.Dto.SignupDto;
import com.example.backend.Services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name="User")
@RequestMapping("/api/auth")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> Signup(@Valid @RequestBody SignupDto signupDto){
        return userService.signup(signupDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> Login(@Valid @RequestBody LoginDto loginDto) {
        return (userService.login(loginDto));
    }
}
