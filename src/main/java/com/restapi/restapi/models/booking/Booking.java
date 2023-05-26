package com.restapi.restapi.models.booking;

import com.restapi.restapi.models.user.User;
import com.restapi.restapi.models.vanue.Venue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private User booker;

    @ManyToOne
    @JoinColumn
    private Venue venue;

    private Integer guests;
    private Date bookingStart;
    private Date bookingEnd;
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
