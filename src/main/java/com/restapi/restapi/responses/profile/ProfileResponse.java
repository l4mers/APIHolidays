package com.restapi.restapi.responses.profile;

import com.restapi.restapi.models.memories.Memory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private long id;
    private String name;
    private String picture;
    private String banner;
    private List<String> languages;
    //@Lob
    private String bio;
    private String liveIn;

    private List<Memory> memories;

    private List<UserVenue> venues;

}
