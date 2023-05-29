package com.restapi.restapi.models.venue;

import com.restapi.restapi.models.booking.Booking;
import com.restapi.restapi.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venue {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private boolean available;
    @ManyToOne
    @JoinColumn
    private User owner;
    @ManyToMany
    @JoinTable
    private List<Amenity> amenity;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> rating;
    @OneToOne(cascade = CascadeType.ALL)
    private VenueInfo info;
    @OneToOne(cascade = CascadeType.ALL)
    private VenueLocation venueLocation;
    @OneToMany(cascade = CascadeType.ALL)
    private List<VenueMedia> venueMedia;
    @OneToMany(mappedBy = "venue")
    private List<Booking> bookings;
    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

}
