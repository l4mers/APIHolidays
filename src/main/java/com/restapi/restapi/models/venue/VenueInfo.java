package com.restapi.restapi.models.venue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VenueInfo {
    @Id
    @GeneratedValue
    private Long id;
    private Integer price;
    private Integer guestQuantity;
    private Integer beds;
    private Integer bathrooms;
    private Double squareMeter;
    @Lob
    private String description;
}
