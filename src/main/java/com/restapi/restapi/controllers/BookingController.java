package com.restapi.restapi.controllers;

import com.restapi.restapi.models.booking.Booking;
import com.restapi.restapi.models.vanue.Venue;
import com.restapi.restapi.repositories.BookingRepository;
import com.restapi.restapi.repositories.UserRepository;
import com.restapi.restapi.repositories.VenueRepository;
import com.restapi.restapi.request.BookingRequest;
import com.restapi.restapi.responses.booking.BookedVenue;
import com.restapi.restapi.responses.booking.FullBookingResponse;
import com.restapi.restapi.responses.booking.TotalBookingResponse;
import com.restapi.restapi.responses.venue.OwnerVenueBookingResponse;
import com.restapi.restapi.responses.venue.VenueProfileUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final VenueRepository venueRepository;

    @PostMapping("/get/booking")
    public ResponseEntity<String> addBooking(@RequestParam Long userId,
                                             @RequestParam Long venueId,
                                             @RequestBody BookingRequest bookingRequest){
        bookingRepository.save(Booking.builder()
                        .booker(userRepository.findById(userId).get())
                        .venue(venueRepository.findById(venueId).get())
                        .bookingStart(bookingRequest.getStart())
                        .bookingEnd(bookingRequest.getEnd())
                .build());

        return ResponseEntity.ok("done");
    }

    @GetMapping("/get/booking/{venueId}")
    public ResponseEntity<List<Booking>> venueBookings(@PathVariable Long venueId){
        return ResponseEntity.ok(bookingRepository.findBookingsByVenue(
                venueRepository.findById(venueId).get()));
    }
    @GetMapping("/get/booking/user/{userId}")
    public ResponseEntity<List<TotalBookingResponse>> userBookings(@PathVariable Long userId){

        return ResponseEntity.ok(bookingRepository.findBookingsByBooker(
                userRepository.findById(userId).get())
                .stream().map(e-> TotalBookingResponse.builder()
                        .booker(VenueProfileUser.builder()
                                .id(e.getBooker().getId())
                                .name(e.getBooker().getInfo().getFirstName() + " " + e.getBooker().getInfo().getLastName())
                                .avatar(e.getBooker().getMedia().getAvatar())
                                .build())
                        .venue(BookedVenue.builder()
                                .venueId(e.getVenue().getId())
                                .title(e.getVenue().getTitle())
                                .owner(VenueProfileUser.builder()
                                        .id(e.getVenue().getOwner().getId())
                                        .name(e.getVenue().getOwner().getInfo().getFirstName() + " "
                                         + e.getVenue().getOwner().getInfo().getLastName())
                                        .avatar(e.getVenue().getOwner().getMedia().getAvatar())
                                        .build())
                                .build())
                        .start(e.getBookingStart())
                        .end(e.getBookingEnd())
                        .build())
                .toList());
    }

    @GetMapping("/get/booking/venue/{venueId}")
    public ResponseEntity<List<TotalBookingResponse>> venueBooking(@PathVariable Long venueId){

        return ResponseEntity.ok(bookingRepository.findBookingsByVenue(
                        venueRepository.findById(venueId).get())
                .stream().map(e-> TotalBookingResponse.builder()
                        .booker(VenueProfileUser.builder()
                                .id(e.getBooker().getId())
                                .name(e.getBooker().getInfo().getFirstName() + " " + e.getBooker().getInfo().getLastName())
                                .avatar(e.getBooker().getMedia().getAvatar())
                                .build())
                        .venue(BookedVenue.builder()
                                .venueId(e.getVenue().getId())
                                .title(e.getVenue().getTitle())
                                .owner(VenueProfileUser.builder()
                                        .id(e.getVenue().getOwner().getId())
                                        .name(e.getVenue().getOwner().getInfo().getFirstName() + " "
                                                + e.getVenue().getOwner().getInfo().getLastName())
                                        .avatar(e.getVenue().getOwner().getMedia().getAvatar())
                                        .build())
                                .build())
                        .start(e.getBookingStart())
                        .end(e.getBookingEnd())
                        .build())
                .toList());
    }

    @GetMapping("/get/booking/owner/{userId}")
    public ResponseEntity<List<OwnerVenueBookingResponse>> ownerBookings(@PathVariable Long userId){
        List<Venue> venues = venueRepository.findAllByOwner(userRepository.findById(userId).get());

        List<OwnerVenueBookingResponse> response = new ArrayList<>();

        venues.forEach(e->{
            e.getBookings().forEach(ee->{
                response.add(OwnerVenueBookingResponse.builder()
                       .venueId(e.getId())
                       .venueTitel(e.getTitle())
                       .bookings(FullBookingResponse.builder()
                               .booker(VenueProfileUser.builder()
                                       .id(ee.getBooker().getId())
                                       .name(ee.getBooker().getInfo().getFirstName() + " " + ee.getBooker().getInfo().getLastName())
                                       .avatar(ee.getBooker().getMedia().getAvatar())
                                       .build())
                               .start(ee.getBookingStart())
                               .end(ee.getBookingEnd())
                               .build())
                       .build());
            });
        });
        return ResponseEntity.ok(response);

    }
//    @GetMapping("/get/booking/user/{userId}")
//    public ResponseEntity<List<TotalBookingResponse>> userBookings(@PathVariable Long userId){
//
//        return ResponseEntity.ok(TotalBookingResponse);
//    }
}
