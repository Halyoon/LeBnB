package com.halyoon.app.stay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StayRepository extends JpaRepository<Stay,Integer> {
    @Query("SELECT COUNT(*) FROM Stay")
    Optional<Integer> Length();

}
