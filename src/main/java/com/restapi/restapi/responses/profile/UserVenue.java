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
    String image;
    String title;
    int rating;
    int price;
}
