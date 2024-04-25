package com.mindinventory.siddhant.photos.web;

import com.mindinventory.siddhant.photos.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class DownloadController {

    @Autowired
    private PhotosService photosService;

    @GetMapping
    public ResponseEntity<byte[]> download(Integer id) {
        return Optional.ofNullable(photosService.get(id))
                .map(photo -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.valueOf(photo.getContentType()));
                    ContentDisposition content = ContentDisposition.builder("attachment").filename(photo.getFileName()).build();
                    headers.setContentDisposition(content);
                    return new ResponseEntity<>(photo.getData(), headers, HttpStatus.OK);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }
}
