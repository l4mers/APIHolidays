package com.restapi.restapi.responses.venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueProfileLocation {

    private String street;
    private String city;
    private String zip;
    private String country;
    private Double lat;
    private Double lng;
    private String placeId;
    private String state;
}
