package com.example.backend.Services;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.Dto.LoginDto;
import com.example.backend.Dto.SignupDto;
import com.example.backend.Entity.Role;
import com.example.backend.Entity.User;
import com.example.backend.Repository.RoleRepository;
import com.example.backend.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    
    @Override
    public ResponseEntity<String>signup(SignupDto signupDto){

        if(userRepository.existsByUsername(signupDto.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already exist.");
        }

        if(userRepository.existsByEmail(signupDto.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already exist.");
        }

        User user = new User();
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());

        String hashedPassword = hashPassword(signupDto.getPassword());
        user.setPassword(hashedPassword);

        Role userRole = roleRepository.findByName("admin");
        if (userRole != null) {
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        }


        User result = userRepository.save(user);
        if(result!=null){
            return ResponseEntity.ok("User signup successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("signup failed");
        }   
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(LoginDto loginDto) {
        User  user = userRepository.findByUsernameOrEmail(loginDto.getUsernameorEmail(),loginDto.getUsernameorEmail());
        Map<String, Object> response = new HashMap<>();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "User not found"));
        }

        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            response.put("userId", user.getId());
            response.put("username", user.getUsername());

            List<Map<String, Object>> roles = user.getRoles().stream()
                .map(role -> {
                    Map<String, Object> roleMap = new HashMap<>();
                    roleMap.put("name", role.getName());
                    return roleMap;
                })
                .collect(Collectors.toList());

            response.put("roles", roles);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Invalid password"));
        }
    }
}
