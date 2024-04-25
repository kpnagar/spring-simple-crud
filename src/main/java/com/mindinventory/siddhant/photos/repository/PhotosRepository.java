package com.mindinventory.siddhant.photos.repository;

import com.mindinventory.siddhant.photos.models.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}
