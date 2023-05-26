package com.restapi.restapi.responses.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMemory {

    private String title;
    private String description;
    private String publicMemo;

    List<UserMemoryMedia> media;

    private Date created;
    private Date updated;
}
