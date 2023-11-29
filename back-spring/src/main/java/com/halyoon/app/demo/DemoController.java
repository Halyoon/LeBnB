package com.halyoon.app.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/demo-controller")

public class DemoController {
    @GetMapping
    public ResponseEntity<String> sayHello() {


try {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    if (userDetails != null) {
        // Use the UserDetails object to access user information
        return ResponseEntity.ok("Hello from secured endpoint\n" + userDetails);
    }

    return ResponseEntity.ok("Hello from secured endpoint\n");
}
catch (Exception ex)
{
    throw new RuntimeException(ex.getMessage());
}
    }

}
