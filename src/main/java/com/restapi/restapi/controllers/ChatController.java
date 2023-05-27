package com.restapi.restapi.controllers;

import com.restapi.restapi.models.chat.Chat;
import com.restapi.restapi.repositories.ChatRepository;
import com.restapi.restapi.repositories.MessageRepository;
import com.restapi.restapi.responses.chat.NewChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5174/*")
public class ChatController {

    ChatRepository chatRepository;
    MessageRepository messageRepository;

//    @PostMapping("/chat")
//    public EntityResponse<Chat> newChat(@RequestBody String message){
//        return chatRepository.save()
//    }
//
//    @PostMapping("/chat/{id}")
//    public EntityResponse<String> newMessage(@RequestHeader long userId, @RequestBody String message, @PathVariable String id){
//
//    }
//
//    @GetMapping("/chat")
//    public EntityResponse<List<ChatResponse>> newChat(@RequestHeader long userId){
//
//    }
}
