package com.restapi.restapi.request;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMediaRequest {
    private String avatar;
    private String background;
    @Lob
    private String bio;
}
