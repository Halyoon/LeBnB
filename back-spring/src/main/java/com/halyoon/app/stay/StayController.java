package com.halyoon.app.stay;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class StayController {

    private final StayService service;

    @GetMapping
    public ResponseEntity<?> getStays(@RequestParam(required = false) StayRequest page) {
        try {
            List<Stay> stays = this.service.getStays(page);
            return ResponseEntity.ok(stays);
        } catch (Exception var3) {
            return ResponseEntity.ok(var3.getMessage());
        }
    }

    @GetMapping({"/length"})
    public ResponseEntity<?> length() {
        Object stays = this.service.Length();
        return ResponseEntity.ok(stays);
    }
}
