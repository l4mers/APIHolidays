package com.restapi.restapi.models.venue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VenueLocation {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String city;
    private String zip;
    private String country;
    private Double lat;
    private Double lng;
    private String placeId;
    private String state;
}
