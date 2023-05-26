package com.restapi.restapi.responses.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMemoryMedia {

    private String image;
    private String title;
    private Date created;
    private Date updated;

}
