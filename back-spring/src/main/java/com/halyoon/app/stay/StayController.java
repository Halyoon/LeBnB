package com.halyoon.app.stay;

import com.halyoon.app.like.LikeService;
import com.halyoon.app.like.LikedStay;
import com.halyoon.app.review.Review;
import com.halyoon.app.review.ReviewRequest;
import com.halyoon.app.review.ReviewerRequest;
import com.halyoon.app.review.ReviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/stay")
@RequiredArgsConstructor
public class StayController {


    private final StayService stayService;
    private final StayMapper mapper;
    private final LikeService likeService;
    private final ReviewerService reviewerService;

    @GetMapping
    public ResponseEntity<?> getStays(@RequestParam(defaultValue = "0", required = false) Integer page, @RequestParam(defaultValue = "6", required = false) Integer size, @RequestParam(required = false) Integer likeByUser) {
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            if (likeByUser != null) {
                List<Stay> stays = stayService.getStaysLikedByUser(pageRequest, likeByUser);
                List<StayResponse> staysResponse = this.mapper.getStays(stays);
                return ResponseEntity.ok(staysResponse);
            } else {

                List<Stay> stays = this.stayService.getStays(pageRequest);
                List<StayResponse> staysResponse = this.mapper.getStays(stays);
                return ResponseEntity.ok(staysResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }


    @GetMapping({"/user-stays"})
    public ResponseEntity<?> getUserStays(@RequestParam(defaultValue = "0", required = false) Integer page, @RequestParam(required = true) Integer hostId, @RequestParam(defaultValue = "6", required = false) Integer size) {
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            List<Stay> stays = this.stayService.getUserStays(hostId, pageRequest);
            List<StayResponse> staysResponse = this.mapper.getStays(stays);
            return ResponseEntity.ok(staysResponse);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }


    @GetMapping({"/length"})
    public ResponseEntity<?> length() {
        Object stays = this.stayService.Length();
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

    @PostMapping("/like")
    public ResponseEntity<?> updateStay(@RequestBody StayUserRequest stayUserRequest) {
        try {
            // Extract stay and user information from the DTO

            var stayId = stayUserRequest.getStayId();
            var userId = stayUserRequest.getUserId();

            // Your existing logic to save stay and user
            LikedStay savedStay = this.likeService.saveLikeLikedByUser(stayId, userId);

            return ResponseEntity.ok(savedStay.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStayById(@PathVariable Integer id) {
        try {
            Stay stay = this.stayService.getStayById(id);
            List<StayResponse> staysResponse = this.mapper.getStays(List.of(stay));
            return ResponseEntity.ok(staysResponse.get(0));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/review")
    public ResponseEntity<?> review(@RequestBody ReviewerRequest reviewRequest) {
        try {
            // Extract stay and user information from the DTO

            var stayId = reviewRequest.getStayId();
            var text = reviewRequest.getTxt();
            // Your existing logic to save stay and user
            Review savedReview = this.reviewerService.createReview(stayId, reviewRequest, text);

            return ResponseEntity.ok(savedReview.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


}
