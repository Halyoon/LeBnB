package com.halyoon.app.like;

import com.halyoon.app.stay.Stay;
import com.halyoon.app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikedStayRepository extends JpaRepository<LikedStay,Integer> {
    Optional<LikedStay> findByUserAndStay(User user, Stay stay);
}
