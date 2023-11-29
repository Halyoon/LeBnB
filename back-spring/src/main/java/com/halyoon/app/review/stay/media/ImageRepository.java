package com.halyoon.app.review.stay.media;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<StayImages,Integer> {
//    @Query("SELECT i FROM StayImages i WHERE i.stay.id = :stayId")
    List<StayImages> findByStayId(Integer stayId);

}
