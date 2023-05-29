package com.restapi.restapi.responses.venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueBrowseResponse {
    private Long id;
    private String title;
    private int beds;
    private int bathrooms;
    private int price;
    private int guests;
    private double rating;
    private double squareMeter;
    private String coverPhoto;
    private List<VenueBookingResponse> bookings;
    private VenueProfileLocation location;
    private List<String> amenities;
    private Date created;
}
