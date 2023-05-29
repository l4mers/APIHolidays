package com.restapi.restapi.responses.home;


import com.restapi.restapi.models.venue.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeScreen {

    private List<TrendingVenue> trendingVenues = new ArrayList<>();
    private List<AffordableVenue> affordableVenues = new ArrayList<>();
    private List<TrendingCountries> trendingCountries = new ArrayList<>();


    public void trendingVenue(Venue venue){
        trendingVenues.add(new TrendingVenue(venue));
    }

    public void affordableVenue(Venue venue){
        affordableVenues.add(new AffordableVenue(venue));
    }

    public void trendingCountries(Venue venue){
        trendingCountries.add(new TrendingCountries(venue));
    }
}