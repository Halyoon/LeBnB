package com.halyoon.app.stay;

import com.halyoon.app.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface StayRepository extends JpaRepository<Stay,Integer> {
    Page<Stay> findAll(Pageable pageable);
    Page<Stay> findByUserId(Integer userId, Pageable pageable);
    // Updated method to find stays liked by a user with a specific userId
    @Query("SELECT s FROM Stay s JOIN s.likedByUsers u WHERE u.id = :userId")
    Page<Stay> findStaysLikedByUserId(Integer userId, Pageable pageable);

    @Query("SELECT u.id FROM Stay s JOIN s.likedByUsers u WHERE s.id = :stayId")
    List<Long> findLikedUserIdsByStayId(Long stayId);
    @Query("SELECT COUNT(*) FROM Stay")
    Optional<Integer> Length();

}
