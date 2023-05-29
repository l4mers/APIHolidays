package com.restapi.restapi.controllers;

import com.restapi.restapi.models.vanue.Amenity;
import com.restapi.restapi.repositories.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5174")
public class AmenityController {

    private final AmenityRepository amenityRepository;

    @GetMapping("get/amenities")
    public ResponseEntity<Iterable<Amenity>> getAllAmenities(){
        return ResponseEntity.ok(amenityRepository.findAll());
    }
}
