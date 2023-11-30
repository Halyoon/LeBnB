package com.halyoon.app.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_EMPTY)//if user not enabled 2fa the secretImageUri that well be empty well not be send in the api.
public class AuthenticationResponse {

    private String token;
    private String fullname;
    private Integer userMsg;
    private Integer hostMsg;
    private String _id;
    private String username;
    private String imgUrl;

}
