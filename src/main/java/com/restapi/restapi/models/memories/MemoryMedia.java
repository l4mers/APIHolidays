package com.restapi.restapi.models.memories;

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
public class MemoryMedia {

    @Id
    @GeneratedValue
    private Integer id;

    private String image;
    private String title;
    @ManyToOne
    @JoinColumn
    private Memory memory;
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
