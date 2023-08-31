package com.example.backend.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;

@Service
public class ImgServiceImpl implements ImgService {
    private final String imageDir = "concertImg/";

    public ResponseEntity<Resource> serverImg(@PathVariable String imageName) {
        try {
            
             // Get the absolute path to the image file
             Path imagePath = Paths.get(imageDir + imageName).normalize();
             File imageFile = imagePath.toFile();
 
             // Check if the file exists
             if (imageFile.exists()) {
                 Resource resource = new UrlResource(imageFile.toURI());
 
                 // Set the content type to "image/png"
                 HttpHeaders headers = new HttpHeaders();
                 headers.setContentType(MediaType.IMAGE_PNG);
 
                 return new ResponseEntity<>(resource, headers, HttpStatus.OK);
             } else {
                 return ResponseEntity.notFound().build();
             }
         } catch (IOException e) {
             return ResponseEntity.status(500).build();
         }
    }
    
}
