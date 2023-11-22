package com.halyoon.app.auth;


import com.halyoon.app.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private boolean mfaEnabled;
        private Role role;
        private String secret;
        private String imgUrl;
        private boolean isSuperhost;
        private String location;
        private String about;
        private String responseTime;
    }



