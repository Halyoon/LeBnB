package com.halyoon.app.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {


//    public User getUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = User.builder()
//                .email(userDetails.getUsername())
//                .build();
//        return user;
//    }
public String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
        // The authenticated user details
        Object principal = authentication.getPrincipal();
        return "Current User: " + principal.toString();
    } else {
        return "No authenticated user";
    }
}
}
