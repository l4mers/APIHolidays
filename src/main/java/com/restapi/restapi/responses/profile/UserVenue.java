package com.restapi.restapi.responses.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVenue {
    Long id;
    String title;
    String image;
    Double rating;
    int price;
}
