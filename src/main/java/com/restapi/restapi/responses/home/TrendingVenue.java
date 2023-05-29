package com.restapi.restapi.responses.home;

import com.restapi.restapi.models.venue.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrendingVenue {
    private Long id;
    private String image;
    //private int rating;
    private String title;
    private String description;
    private int price;

    public TrendingVenue(Venue venue) {
        this.id = venue.getId();
        this.image = venue.getVenueMedia().get(0).getImage();
        this.title = venue.getTitle();
        this.description = venue.getInfo().getDescription();
        this.price = venue.getInfo().getPrice();

    }
}
