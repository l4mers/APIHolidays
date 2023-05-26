package com.restapi.restapi.responses.venue;

import jakarta.persistence.Lob;
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
public class VenueProfile {
    private Long id;
    private String title;
    private VenueProfileUser owner;
    private List<String> amenities;
    private List<VenueProfileRating> venueProfileRatings;
    private Integer price;
    private Integer guestQuantity;
    private Integer beds;
    private Double squareMeter;
    private Integer bathrooms;
    @Lob
    private String description;
    private VenueProfileLocation location;
    private List<VenueProfileMedia> media;
    private List<VenueBookingResponse> bookings;
    private Date created;
    private Date updated;
}
