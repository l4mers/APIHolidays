package com.restapi.restapi.responses.user;

import com.restapi.restapi.responses.profile.UserMemory;
import com.restapi.restapi.responses.profile.UserVenue;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private long id;
    private String name;
    private String avatar;
    private String banner;
    @Lob
    private String bio;
    private String liveIn;
    private List<String> languages;
    private List<UserMemory> memories;
    private List<UserVenue> venues;

}
