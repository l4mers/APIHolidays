package com.restapi.restapi.responses.venue;

import com.restapi.restapi.responses.booking.FullBookingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerVenueBookingResponse {
    private Long venueId;
    private String venueTitel;
    private FullBookingResponse bookings;
}
