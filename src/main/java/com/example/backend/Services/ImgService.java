package com.example.backend.Services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ImgService {
    ResponseEntity<Resource> serverImg(String imageName);
}
