package com.mindinventory.siddhant.photos.web;

import com.mindinventory.siddhant.photos.service.PhotosService;
import com.mindinventory.siddhant.photos.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class PhotosController {

    @Autowired
    private PhotosService photosService;


    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photos")
    public Iterable<Photo> get() {
        return photosService.getAll();
    }

    @GetMapping("/photos/{id}")
    public Photo getByID(@PathVariable Integer id) {
        return photosService.get(id);
    }

    @DeleteMapping("/photos/{id}")
    public void delByID(@PathVariable Integer id) {
        photosService.delete(id);
    }

    @PostMapping("/photos/")
    public Photo upload(@RequestPart("data") MultipartFile file) throws IOException {
        return photosService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
