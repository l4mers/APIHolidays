package com.restapi.restapi.models.venue;

import com.restapi.restapi.models.user.User;
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
public class Rating {

    @Id
    @GeneratedValue
    private Long id;
    private Integer rating;
    private String comment;
    @ManyToOne
    @JoinColumn
    private User rater;
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
