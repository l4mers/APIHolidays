package com.restapi.restapi.authentication;

import com.restapi.restapi.config.JwtService;
import com.restapi.restapi.models.user.Role;
import com.restapi.restapi.models.user.User;
import com.restapi.restapi.models.user.UserInfo;
import com.restapi.restapi.repositories.UserRepository;
import com.restapi.restapi.responses.user.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        User user = repository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .info(UserInfo.builder()
                        .firstName(request.getFirstname())
                        .lastName(request.getLastname())
                        .birthDate(request.getBirthDate())
                        .build())
                .build());
        //var jwtToken = jwtService.generateToken();

        return AuthenticationResponse.builder()
                .user(UserLoginResponse.builder()
                        .id(user.getId())
                        .name(user.getInfo().getFirstName() + " " + user.getInfo().getLastName())
                        .birthDay(user.getInfo().getBirthDate())
                        .build())
                .token(jwtService.generateToken(User.builder()
                        .email(user.getEmail())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .build()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
//        if(repository.existsByEmail(request.getEmail())){
        // var jwtToken = jwtService.generateToken();
        return AuthenticationResponse.builder()
                .user(UserLoginResponse.builder()
                        .id(user.getId())
                        .name(user.getInfo().getFirstName() + " " + user.getInfo().getLastName())
                        .birthDay(user.getInfo().getBirthDate())
                        .build())
                .token(jwtService.generateToken(User.builder()
                        .email(user.getEmail())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .build()))
                .build();
    }
}
