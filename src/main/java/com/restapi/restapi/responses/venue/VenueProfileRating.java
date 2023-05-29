package com.restapi.restapi.responses.venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueProfileRating {
    private Double rating;
    private VenueProfileUser rater;
    private String comment;
    private Date created;
    private Date updated;
}
