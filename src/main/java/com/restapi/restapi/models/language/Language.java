package com.restapi.restapi.models.language;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Language {

    @Id
    @GeneratedValue
    private Long id;
    private String language;
    public Language(String language) {
        this.language = language;
    }
    public Language() {

    }
}
