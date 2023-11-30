package com.halyoon.app.like;

import com.halyoon.app.stay.Stay;
import com.halyoon.app.stay.StayRepository;
import com.halyoon.app.stay.media.ImageRepository;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserRespository;
import com.halyoon.app.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final StayRepository stayRepository;
    private final LikedStayRepository LikedRepository;
    private final UserService userService;
    private final UserRespository userRespository;
    private final ImageRepository imageRepository;
    /*
    This function is used to save a record of the like of the user
     */
    public LikedStay saveLikeLikedByUser(Integer stayId, Integer userId) {

        //fetch user from DB
        User user = userRespository.getReferenceById(userId);

        //fetch stay from DB
        Stay stay = stayRepository.getReferenceById(stayId);
        Optional<LikedStay> likedStay = LikedRepository.findByUserAndStay(user, stay);

        if (likedStay.isPresent()) {
            // LikedStay found
            LikedStay foundLikedStay = likedStay.get();

            // Delete the LikedStay
            LikedRepository.delete(foundLikedStay);
            LikedStay newlikedStay = LikedStay.builder()
                    .build();
            return newlikedStay;
            // Your additional logic here

            // Return a response or perform other actions as needed
        } else {
            // LikedStay not found
            // Handle , return an error response
            LikedStay newlikedStay = LikedStay.builder()
                    .stay(stay)
                    .user(user)
                    .createdAt(new Date())
                    .build();
            var likedStayUser = LikedRepository.save(newlikedStay);

            return likedStayUser;
        }



    }
}
