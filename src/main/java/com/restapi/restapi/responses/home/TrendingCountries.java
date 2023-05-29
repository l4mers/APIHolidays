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
public class TrendingCountries {

    private String image;
    //private int rating;
    private String country;
    //private String description;
    //private int price;
    //6
    public TrendingCountries(Venue venue){
        this.image = venue.getVenueMedia().get(0).getImage();
        this.country = venue.getVenueLocation().getCountry();
    }
}
