package com.restapi.restapi.controllers;

import com.restapi.restapi.models.venue.*;
import com.restapi.restapi.repositories.AmenityRepository;
import com.restapi.restapi.repositories.UserRepository;
import com.restapi.restapi.repositories.VenueRepository;
import com.restapi.restapi.request.SearchVenueRequest;
import com.restapi.restapi.request.VenueMediaRequest;
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

import java.util.ArrayList;
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
    @GetMapping("get/venues/all")
    public ResponseEntity<List<VenueBrowseResponse>> getAllVenues(){
        return ResponseEntity.ok(venueRepository.findAll().stream().map(e -> VenueBrowseResponse.builder()
                .id(e.getId())
                .title(e.getTitle())
                .amenities(e.getAmenity().stream().map(Amenity::getAmenity).toList())
                //.rating(e.getRating().stream().mapToInt(Rating::getRating).average().getAsDouble())
                .price(e.getInfo().getPrice())
                .guests(e.getInfo().getGuestQuantity())
                .beds(e.getInfo().getBeds())
                .bathrooms(e.getInfo().getBathrooms())
                .squareMeter(e.getInfo().getSquareMeter())
                .location(VenueProfileLocation.builder()
                        .street(e.getVenueLocation().getStreet())
                        .city(e.getVenueLocation().getCity())
                        .zip(e.getVenueLocation().getZip())
                        .country(e.getVenueLocation().getCountry())
                        .lat(e.getVenueLocation().getLat())
                        .lng(e.getVenueLocation().getLng())
                        .placeId(e.getVenueLocation().getPlaceId())
                        .state(e.getVenueLocation().getState())
                        .build())
                .coverPhoto(e.getVenueMedia().get(0).getImage())
                .bookings(e.getBookings().stream().map(eee-> new VenueBookingResponse(eee.getBookingStart(), eee.getBookingEnd())).toList())
                .created(e.getCreated())
                .build()).toList());
    }

//    @GetMapping("/get/search")
//    public List<Venue> searchVenues(@RequestBody SearchVenueRequest searchVenueRequest){
//        if (searchVenueRequest.getPrice() != 0 ||
//        searchVenueRequest.getGuests() != 0 ||
//        !searchVenueRequest.getLocation().equals("") ||
//        searchVenueRequest.getBooking() != null ||
//        searchVenueRequest.getAmenities().size() != 0){
//
//        }
//    }
//
//    private List<Venue> compareLists(List<List<Venue>> lists){
//
//    }

//    @GetMapping("/get/venues/")
//    public List<Venue> getVenuesWithPriceLowerThan(@RequestParam("price") Integer price) {
//        return venueRepository.findVenuesWithPriceLowerThan(price);
//    }

    @PostMapping("get/venue/register/{userId}")
    public ResponseEntity<Long> registerVenue(@PathVariable Long userId,
                                               @RequestBody VenueRequest venueRequest){

        Venue v = Venue.builder()
                .title("title")
                .available(true)
                .owner(userRepository.findById(userId).orElseThrow())
                .amenity(amenityRepository.findByAmenityIn(venueRequest.getAmenities()))
                .info(VenueInfo.builder()
                        .price(venueRequest.getPrice())
                        .squareMeter(venueRequest.getSquareMeter())
                        .beds(venueRequest.getBeds())
                        .guestQuantity(venueRequest.getGuests())
                        .bathrooms(venueRequest.getBathrooms())
                        .description(venueRequest.getDescription())
                        .bathrooms(venueRequest.getBathrooms())
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
                .venueMedia(new ArrayList<>())
                .build();

        List<VenueMedia> venueMedia = venueRequest.getMedia().stream().map(e -> VenueMedia.builder()
                .image(e.getImage())
                .description(e.getDescription())
                .venue(v)
                .build()).toList();

        v.setVenueMedia(venueMedia);

        return ResponseEntity.ok(venueRepository.save(v).getId());
    }

    @PutMapping("get/venue/update/{id}")
    public ResponseEntity<Long> updateVenue(@PathVariable Long id,
                                            @RequestBody VenueRequest venueRequest){
        Venue venue = venueRepository.findById(id)
                .orElseThrow();
        venue.setTitle(venue.getTitle());
        venue.setAmenity(amenityRepository.findByAmenityIn(venueRequest.getAmenities()));
        venue.setInfo(VenueInfo.builder()
                .price(venueRequest.getPrice())
                .squareMeter(venueRequest.getSquareMeter())
                .beds(venueRequest.getBeds())
                .guestQuantity(venueRequest.getGuests())
                .bathrooms(venueRequest.getBathrooms())
                .description(venueRequest.getDescription())
                .build());

        List<VenueMedia> venueMedia = venueRequest.getMedia().stream().map(e -> VenueMedia.builder()
                .image(e.getImage())
                .description(e.getDescription())
                .venue(venue)
                .build()).toList();

        venue.setVenueMedia(venueMedia);

        return ResponseEntity.ok(venueRepository.save(venue).getId());
    }

    @PutMapping("get/venue/updatetest/{id}")
    public ResponseEntity<Long> updateVenueTest(@PathVariable Long id,
                                            @RequestBody VenueMediaRequest venueRequest){
        Venue venue = venueRepository.findById(id).orElseThrow();
        List<VenueMedia> venueMedia = List.of(VenueMedia.builder()
                .image(venueRequest.getImage())
                .description(venueRequest.getDescription())
                .venue(venue)
                .build());
        venue.setVenueMedia(venueMedia);


//        venue.setTitle(venue.getTitle());
//        venue.setAmenity(amenityRepository.findByAmenityIn(venueRequest.getAmenities()));
//        venue.setInfo(VenueInfo.builder()
//                .price(venueRequest.getPrice())
//                .squareMeter(venueRequest.getSquareMeter())
//                .beds(venueRequest.getBeds())
//                .guestQuantity(venueRequest.getGuests())
//                .bathrooms(venueRequest.getBathrooms())
//                .description(venueRequest.getDescription())
//                .build());
//
//        List<VenueMedia> venueMedia = venueRequest.getMedia().stream().map(e -> VenueMedia.builder()
//                .image(e.getImage())
//                .description(e.getDescription())
//                .venue(venue)
//                .build()).toList();
//
//        venue.setVenueMedia(venueMedia);

        return ResponseEntity.ok(venueRepository.save(venue).getId());
    }

    @DeleteMapping("get/venue/delete/{venueId}")
    public ResponseEntity<String> deleteVenue(@PathVariable Long venueId){
        venueRepository.deleteById(venueId);
        return ResponseEntity.ok("deleted");
    }
}
