package com.restapi.restapi.responses.user;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMediaResponse {
    private String avatar;
    private String background;
    @Lob
    private String bio;
}
