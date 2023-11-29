package com.halyoon.app.demo;


import com.halyoon.app.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo-controller")
public class DemoController {

    private final UserService userService;

    public DemoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String sayHello() {
        return userService.getCurrentUser();

    }
}
