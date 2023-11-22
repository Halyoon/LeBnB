package com.halyoon.app.stay.media;

import com.halyoon.app.stay.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<StayImages,Integer> {
//    @Query("SELECT i FROM StayImages i WHERE i.stay.id = :stayId")
    List<StayImages> findByStayId(Integer stayId);

}
