package com.restapi.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchVenueRequest {
    private Integer price;
    private Integer guests;
    private String location;
    private BookingRequest booking;
    private List<String> amenities;
}
