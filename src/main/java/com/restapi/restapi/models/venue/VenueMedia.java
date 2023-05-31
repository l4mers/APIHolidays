package com.restapi.restapi.models.venue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VenueMedia {
    @Id
    @GeneratedValue
    private Long id;
    private String image;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Venue venue;
}
