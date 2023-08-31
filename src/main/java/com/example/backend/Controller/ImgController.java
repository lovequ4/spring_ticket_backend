package com.example.backend.Controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Services.ImgService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@Tag(name="imgURL")
@RequestMapping("concertImg")
public class ImgController {
    private ImgService imgService;

    public  ImgController(ImgService imgService) {
        this.imgService = imgService;
    }

    @GetMapping("{imageName:.+}")
    public ResponseEntity<Resource> GetConcertImg(@PathVariable("imageName") String imageName){
        return imgService.serverImg(imageName);
    }
}
