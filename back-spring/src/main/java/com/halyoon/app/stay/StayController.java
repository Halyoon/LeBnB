package com.halyoon.app.stay;

import com.halyoon.app.stay.media.ImageRepository;
import com.halyoon.app.stay.media.StayImages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/stay")
@RequiredArgsConstructor
public class StayController {

    private final StayService service;
    private final StayService stayService;
    private final StayMapper mapper;

    @GetMapping
    public ResponseEntity<?> getStays(@RequestParam(required = false) Integer page) {
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

    @PostMapping()
    public ResponseEntity<?> createStay(@RequestBody StayRequest stayRequest) {
        try {
            Stay savedStay = this.stayService.saveStay(stayRequest);
            return ResponseEntity.ok(savedStay.getId());
        } catch (Exception e) {
            // Logging the exception might be helpful for debugging
            //log.error("Error saving Stay and StayImages", ex);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
