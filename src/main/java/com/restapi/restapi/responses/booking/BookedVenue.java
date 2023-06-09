package com.restapi.restapi.responses.booking;

import com.restapi.restapi.responses.venue.VenueProfileUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookedVenue {
    private Long venueId;
    private String title;
    private VenueProfileUser owner;
}
