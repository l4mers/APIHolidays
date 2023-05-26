package com.restapi.restapi.responses.home;

import com.restapi.restapi.models.vanue.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffordableVenue {
    private Long id;
    private String image;
    //private int priceScore;
    private int price;

    public AffordableVenue(Venue venue){
        this.id = venue.getId();
        this.image = venue.getVenueMedia().get(0).getImage();
        this.price = venue.getInfo().getPrice();
    }
}
