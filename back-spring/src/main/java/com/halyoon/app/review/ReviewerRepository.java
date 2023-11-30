package com.halyoon.app.review;

import com.halyoon.app.like.LikedStay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewerRepository extends JpaRepository<Review, Integer> {
    List<Review> findByStayId(Integer stayId);
}
