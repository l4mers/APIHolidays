package com.restapi.restapi.controllers;

import com.restapi.restapi.models.booking.Booking;
import com.restapi.restapi.repositories.BookingRepository;
import com.restapi.restapi.repositories.UserRepository;
import com.restapi.restapi.repositories.VenueRepository;
import com.restapi.restapi.request.BookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final VenueRepository venueRepository;

    @PutMapping("/get/booking/")
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

    @GetMapping("/get/booking/{id}")
    public ResponseEntity<List<Booking>> venueBookings(@PathVariable Long venueId){
        return ResponseEntity.ok(bookingRepository.findBookingsByVenue(
                venueRepository.findById(venueId).get()));
    }
}
