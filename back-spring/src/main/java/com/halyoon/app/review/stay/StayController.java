package com.halyoon.app.review.stay;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<?> getStays(@RequestParam(defaultValue = "0",required = false) Integer page ,@RequestParam(defaultValue = "6",required = false) Integer size) {
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            List<Stay> stays = this.service.getStays(pageRequest);
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

    @PostMapping("/create")
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getStayById(@PathVariable Integer id) {
        try {
            Stay stay = this.service.getStayById(id);
            List<StayResponse> staysResponse = this.mapper.getStays(List.of(stay));
            return ResponseEntity.ok(staysResponse.get(0));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


}
