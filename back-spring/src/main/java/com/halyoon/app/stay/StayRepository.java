package com.halyoon.app.stay;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface StayRepository extends JpaRepository<Stay,Integer> {
    Page<Stay> findAll(Pageable pageable);
    Page<Stay> findByUserId(Integer userId, Pageable pageable);
    @Query("SELECT COUNT(*) FROM Stay")
    Optional<Integer> Length();

}
