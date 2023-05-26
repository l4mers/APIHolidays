package com.restapi.restapi.responses.booking;

import com.restapi.restapi.responses.venue.VenueProfileUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TotalBookingResponse {

    private BookedVenue venue;
    private VenueProfileUser booker;
    private Date start;
    private Date end;

}
