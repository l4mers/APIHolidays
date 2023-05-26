package com.restapi.restapi.request;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {

    private double squareMeter;
    private int guests;
    private int beds;
    private int bathrooms;

    private String title;
    @Lob
    private String description;

    private String street;
    private String city;
    private String zip;
    private String country;
    private Double lat;
    private Double lng;
    private String placeId;
    private String state;

    List<String> amenities;

    int price;
    private List<VenueMediaRequest> media;
}
