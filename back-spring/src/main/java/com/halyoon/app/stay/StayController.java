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
    private final StayMapper mapper;

    @GetMapping
    public ResponseEntity<?> getStays(@RequestParam(required = false) StayRequest page) {
        try {
            List<Stay> stays = this.service.getStays(page);
            List<StayResponse> staysResponse = this.mapper.getStays(stays);
            return ResponseEntity.ok(staysResponse);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping({"/length"})
    public ResponseEntity<?> length() {
        Object stays = this.service.Length();
        return ResponseEntity.ok(stays);
    }
}
