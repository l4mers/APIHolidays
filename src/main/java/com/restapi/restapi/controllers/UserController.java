package com.restapi.restapi.controllers;

import com.restapi.restapi.models.user.User;
import com.restapi.restapi.repositories.UserRepository;
import com.restapi.restapi.responses.user.UserMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    UserRepository userRepository;


    /*
    Byt
    "@GetMapping("user/menu/response")
    public ResponseEntity<UserMenuResponse> homeScreen(@RequestHeader long userId){
        User user = userRepository.findById(userId).get();"

     med

     "@GetMapping("user/menu/response/{id}")
    public ResponseEntity<UserMenuResponse> homeScreen(@PathVariable long id){
        User user = userRepository.findById(id).get();"

    */

//    @PostMapping("user/menu/response")
//    public ResponseEntity<UserMenuResponse> homeScreen(@RequestHeader long userId){
//        User user = userRepository.findById(userId).get();
//        return ResponseEntity.ok(UserMenuResponse.builder()
//                        .id(user.getId())
//                        .name(user.getInfo().getFirstName() + " " + user.getInfo().getFirstName())
//                        .avatar(user.getMedia().getAvatar())
//                        .email(user.getEmail())
//                .build());
//    }
    @PostMapping("user/menu/response")
    public ResponseEntity<String> homeScreen(){
        return ResponseEntity.ok("oyea");
//        User user = userRepository.findById(userId).get();
//        return ResponseEntity.ok(UserMenuResponse.builder()
//                .id(user.getId())
//                .name(user.getInfo().getFirstName() + " " + user.getInfo().getFirstName())
//                .avatar(user.getMedia().getAvatar())
//                .email(user.getEmail())
//                .build());
    }
}
