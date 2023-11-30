package com.halyoon.app.review;

import com.halyoon.app.stay.Stay;
import com.halyoon.app.stay.StayRepository;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserRespository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewerService {
    private final ReviewerRepository reviewerRepository;
    private final UserRespository userRespository;
    private final StayRepository stayRepository;


    public Review createReview(Integer stayId, ReviewerRequest reviewerRequest, String reviewText) {
        // Assuming you have methods to find User and Stay by their respective IDs
        // You need to implement these methods in your User and Stay services or repositories

        var userId = reviewerRequest.getBy().get_id();
        User reviewer = userRespository.getReferenceById(userId);
        Stay stay = stayRepository.getReferenceById(stayId);

        // Create a new Review object
        Review review = Review.builder()
                .reviewAt(new Date())
                .reviewer(reviewer)
                .stay(stay)
                .reviewText(reviewText)
                .build();

        // Save the review to the database
        return reviewerRepository.save(review);
    }

    public List<Review> getReviewsForStay(Integer stayId) {
        return reviewerRepository.findByStayId(stayId);
    }
}
