package com.restapi.restapi.controllers;

import com.restapi.restapi.models.vanue.*;
import com.restapi.restapi.repositories.AmenityRepository;
import com.restapi.restapi.repositories.UserRepository;
import com.restapi.restapi.repositories.VenueRepository;
import com.restapi.restapi.request.VenueRequest;
import com.restapi.restapi.responses.home.AffordableVenue;
import com.restapi.restapi.responses.home.HomeScreen;
import com.restapi.restapi.responses.home.TrendingCountries;
import com.restapi.restapi.responses.home.TrendingVenue;
import com.restapi.restapi.responses.venue.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class VenueController {

    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final AmenityRepository amenityRepository;

    @GetMapping("get/home")
    public ResponseEntity<HomeScreen> homeScreen(){
        List<Venue> ratingList = venueRepository.findVenueWithHighestAverageRating(PageRequest.of(0, 2));
        List<Venue> budgetList = venueRepository.findVenueByLowestPricePerSquareMeter(PageRequest.of(0, 6));
        List<Venue> countryList = venueRepository.findVenuesByMostPopularCountry(PageRequest.of(0, 6));
        HomeScreen homeScreen = new HomeScreen();
        ratingList.forEach(homeScreen::trendingVenue);
        budgetList.forEach(homeScreen::affordableVenue);
        countryList.forEach(homeScreen::trendingCountries);
        return ResponseEntity.ok(homeScreen);
    }

    @GetMapping("get/venue/rating")
    public ResponseEntity<List<TrendingVenue>> venueByRating(@RequestParam(value = "page", defaultValue = "0") int page,
                                                            @RequestParam(value = "limit", defaultValue = "30") int limit){
        return ResponseEntity.ok(venueRepository.findVenueWithHighestAverageRating(
                PageRequest.of(page, limit))
                .stream().map(TrendingVenue::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("get/venue/budget")
    public ResponseEntity<List<AffordableVenue>> venueByBudget(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "limit", defaultValue = "30") int limit){
        return ResponseEntity.ok(venueRepository.findVenueByLowestPricePerSquareMeter(
                        PageRequest.of(page, limit))
                .stream().map(AffordableVenue::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("get/venue/country")
    public ResponseEntity<List<TrendingCountries>> venueByCountry(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "limit", defaultValue = "30") int limit){
        return ResponseEntity.ok(venueRepository.findVenueByLowestPricePerSquareMeter(
                        PageRequest.of(page, limit))
                .stream().map(TrendingCountries::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("get/venue/{id}")
    public ResponseEntity<VenueProfile> singleVenue(@PathVariable Long id){
        Venue venue = venueRepository.findById(id).get();
        return ResponseEntity.ok(VenueProfile.builder()
                        .id(venue.getId())
                        .title(venue.getTitle())
                        .owner(VenueProfileUser.builder()
                                .id(venue.getOwner().getId())
                                .name(venue.getOwner().getInfo().getFirstName() + " " + venue.getOwner().getInfo().getLastName())
                                .avatar(venue.getOwner().getMedia().getAvatar())
                                .build())
                        .amenities(venue.getAmenity().stream().map(Amenity::getAmenity).toList())
                        .venueProfileRatings(venue.getRating().stream().map(e->{
                            VenueProfileRating vpr = new VenueProfileRating();
                            vpr.setComment(e.getComment());
                            vpr.setRater(VenueProfileUser.builder()
                                            .id(e.getRater().getId())
                                            .avatar(e.getRater().getMedia().getAvatar())
                                            .name(e.getRater().getInfo().getFirstName() +
                                                    " " + e.getRater().getInfo().getLastName())
                                    .build());
                            vpr.setComment(e.getComment());
                            vpr.setCreated(e.getCreated());
                            return vpr;
                        }).toList())
                        .price(venue.getInfo().getPrice())
                        .guestQuantity(venue.getInfo().getGuestQuantity())
                        .beds(venue.getInfo().getBeds())
                        .bathrooms(venue.getInfo().getBathrooms())
                        .squareMeter(venue.getInfo().getSquareMeter())
                        .description(venue.getInfo().getDescription())
                        .location(VenueProfileLocation.builder()
                                .street(venue.getVenueLocation().getStreet())
                                .city(venue.getVenueLocation().getCity())
                                .zip(venue.getVenueLocation().getZip())
                                .country(venue.getVenueLocation().getCountry())
                                .lat(venue.getVenueLocation().getLat())
                                .lng(venue.getVenueLocation().getLng())
                                .placeId(venue.getVenueLocation().getPlaceId())
                                .state(venue.getVenueLocation().getState())
                                .build())
                        .media(venue.getVenueMedia().stream().map(e-> new VenueProfileMedia(e.getImage(), e.getDescription())).toList())
                        .bookings(venue.getBookings().stream().map(e-> new VenueBookingResponse(e.getBookingStart(), e.getBookingEnd())).toList())
                        .created(venue.getCreated())
                .build());

    }
    @PostMapping("get/test")
    public ResponseEntity<String> test(@RequestBody String tryMe){
        System.out.println(tryMe);
        if(tryMe.equals("hej")){
            return ResponseEntity.ok("Du skrev hej");
        }
        return ResponseEntity.ok("du skrev inte hej");
    }

    @PostMapping("venue/register/{id}")
    public ResponseEntity<Long> registerVenue(@PathVariable Long id,
                                              @RequestBody VenueRequest venueRequest){

               return ResponseEntity.ok(venueRepository.save(Venue.builder()
                        .title(venueRequest.getTitle())
                        .available(true)
                        .owner(userRepository.findById(id).orElseThrow())
                        .amenity(amenityRepository.findByAmenityIn(venueRequest.getAmenities()))
                        .info(VenueInfo.builder()
                                .price(venueRequest.getPrice())
                                .squareMeter(venueRequest.getSquareMeter())
                                .beds(venueRequest.getBeds())
                                .guestQuantity(venueRequest.getGuests())
                                .description(venueRequest.getDescription())
                                .build())
                        .venueLocation(VenueLocation.builder()
                                .country(venueRequest.getCountry())
                                .zip(venueRequest.getZip())
                                .city(venueRequest.getCity())
                                .street(venueRequest.getStreet())
                                .lat(venueRequest.getLat())
                                .lng(venueRequest.getLng())
                                .placeId(venueRequest.getPlaceId())
                                .state(venueRequest.getState())
                                .build())
                        .venueMedia(venueRequest.getMedia().stream().map(e-> VenueMedia.builder()
                                .image(e.getImage())
                                .description(e.getDescription())
                                .build()).collect(Collectors.toList()))
                .build()).getId());

    }
    @PostMapping("get/venue/register/{id}")
    public ResponseEntity<Long> registerVenueTest(@PathVariable Long id,
                                               @RequestBody VenueRequest venueRequest){

        //Venue venue =
        return ResponseEntity.ok(venueRepository.save(Venue.builder()
                .title(venueRequest.getTitle())
                .available(true)
                .owner(userRepository.findById(id).orElseThrow())
                .amenity(amenityRepository.findByAmenityIn(venueRequest.getAmenities()))
                .info(VenueInfo.builder()
                        .price(venueRequest.getPrice())
                        .squareMeter(venueRequest.getSquareMeter())
                        .beds(venueRequest.getBeds())
                        .guestQuantity(venueRequest.getGuests())
                        .description(venueRequest.getDescription())
                        .build())
                .venueLocation(VenueLocation.builder()
                        .country(venueRequest.getCountry())
                        .zip(venueRequest.getZip())
                        .city(venueRequest.getCity())
                        .street(venueRequest.getStreet())
                        .lat(venueRequest.getLat())
                        .lng(venueRequest.getLng())
                        .placeId(venueRequest.getPlaceId())
                        .state(venueRequest.getState())
                        .build())
                .venueMedia(venueRequest.getMedia().stream().map(e-> VenueMedia.builder()
                        .image(e.getImage())
                        .description(e.getDescription())
                        .build()).collect(Collectors.toList()))
                .build()).getId());

    }
}
