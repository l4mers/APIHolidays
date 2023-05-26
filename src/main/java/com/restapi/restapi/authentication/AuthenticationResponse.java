package com.restapi.restapi.authentication;

import com.restapi.restapi.responses.user.UserLoginResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private UserLoginResponse user;
    private String token;

}
