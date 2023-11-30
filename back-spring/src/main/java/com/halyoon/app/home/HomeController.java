package com.halyoon.app.home;

import com.halyoon.app.auth.AuthenticationResponse;
import com.halyoon.app.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class HomeController {


    @GetMapping
    public ResponseEntity<?> register() {
        String response ="Welcome to Lebnb API's!!";

            return ResponseEntity.ok(response);
    }
}
