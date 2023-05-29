package com.restapi.restapi.controllers;

import com.restapi.restapi.models.language.Language;
import com.restapi.restapi.models.user.User;
import com.restapi.restapi.models.venue.Rating;
import com.restapi.restapi.repositories.UserRepository;
import com.restapi.restapi.request.UserMediaRequest;
import com.restapi.restapi.responses.profile.UserMemory;
import com.restapi.restapi.responses.profile.UserMemoryMedia;
import com.restapi.restapi.responses.profile.UserVenue;
import com.restapi.restapi.responses.user.UserMediaResponse;
import com.restapi.restapi.responses.user.UserMenuResponse;
import com.restapi.restapi.responses.user.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/get/user/profile/{userId}")
    public ResponseEntity<?> userProfile(@PathVariable Long userId){

        User user = userRepository.findById(userId).orElseThrow();
        return ResponseEntity.ok(UserProfileResponse.builder()
                        .id(user.getId())
                        .name(user.getInfo().getFirstName() + " " + user.getInfo().getLastName())
                        .avatar(user.getMedia().getAvatar())
                        .banner(user.getMedia().getBackground())
                        .bio(user.getMedia().getBio())
                        .liveIn(user.getAddress().getCity() + ", " + user.getAddress().getCountry())
                        .languages(user.getInfo().getLanguage().stream().map(Language::getLanguage).toList())
                        .memories(user.getMemories().stream().map(e-> UserMemory.builder()
                                .title(e.getTitle())
                                .description(e.getDescription())
                                .publicMemo(e.getPublicMemo())
                                .created(e.getCreated())
                                .media(e.getMedia().stream().map(ee -> UserMemoryMedia.builder()
                                        .image(ee.getImage())
                                        .title(ee.getTitle())
                                        .created(ee.getCreated())
                                        .build()).toList())
                                .build()).toList())
                        .venues(user.getVenues().stream().map(e-> UserVenue.builder()
                                .id(e.getId())
                                .title(e.getTitle())
                                .image(e.getVenueMedia().get(0).getImage())
                                .price(e.getInfo().getPrice())
                                .rating(e.getRating().stream().mapToInt(Rating::getRating).average().getAsDouble())
                                .build()).toList())
                .build());
    }

    @GetMapping("/get/user/menu/{userId}")
    public ResponseEntity<UserMenuResponse> homeScreen(@PathVariable Long userId){
        User user = userRepository.findById(userId).get();
        return ResponseEntity.ok(UserMenuResponse.builder()
                        .email(user.getEmail())
                        .name(user.getInfo().getFirstName() + " " + user.getInfo().getLastName())
                        .avatar(user.getMedia().getAvatar())
                .build());
    }

    @PutMapping("/get/user/media/{userId}")
    public ResponseEntity<UserMediaResponse> updateUserMedia(@PathVariable Long userId,
                                                             @RequestBody UserMediaRequest userMediaRequest){
        User user = userRepository.findById(userId).get();
        user.getMedia().setAvatar(userMediaRequest.getAvatar());
        user.getMedia().setBackground(userMediaRequest.getBackground());
        user.getMedia().setBio(userMediaRequest.getBio());
        userRepository.save(user);
        return ResponseEntity.ok(UserMediaResponse.builder()
                        .avatar(userMediaRequest.getAvatar())
                        .background(userMediaRequest.getBackground())
                        .bio(userMediaRequest.getBio())
                .build());
    }
}
