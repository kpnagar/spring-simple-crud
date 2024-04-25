package com.mindinventory.siddhant.photos.service;

import com.mindinventory.siddhant.photos.models.Photo;
import com.mindinventory.siddhant.photos.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class PhotosService {

    @Autowired
    private PhotosRepository photosRepository;


    public Iterable<Photo> getAll() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(Integer id) {
        photosRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] bytes) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(bytes);
        return photosRepository.save(photo);
    }
}
