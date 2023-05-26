package com.restapi.restapi.repositories;

import com.restapi.restapi.models.booking.Booking;
import com.restapi.restapi.models.vanue.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findBookingsByVenue(Venue venue);
}
