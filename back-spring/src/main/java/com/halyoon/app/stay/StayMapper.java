package com.halyoon.app.stay;

import com.halyoon.app.like.LikedStay;
import com.halyoon.app.review.Review;
import com.halyoon.app.review.ReviewerResponse;
import com.halyoon.app.review.ReviewerService;
import com.halyoon.app.media.ImageRepository;
import com.halyoon.app.media.StayImages;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserResponse;
import com.halyoon.app.location.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StayMapper {

    private final ImageRepository imageRepository;
    private final ReviewerService reviewerService;

    private UserResponse mapHost(User user) {
        if (user == null)
            return null;
        return UserResponse.builder()
                ._id(user.getId().toString())
                .fullname(user.getFullname())
                .createAt(user.getCreatedAt())
                .about(user.getAbout())
                .location(user.getLocation())
                .responseTime((user.getResponseTime()))
                .pictureUrl(user.getImgUrl())
                .isSuperhost(user.isSuperhost())
                .build();
    }


    private LocationResponse maploc() {

        return LocationResponse.builder()
                .address("Beirut, Lebanon")
                .lat(33.8938)
                .lan(35.5018)
                .city("Beirut")
                .country("Lebanon")
                .countryCode("LB")

                .build();
    }

    public List<StayResponse> getStays(List<Stay> stays) {
        List<StayResponse> list = new ArrayList<>();

        for (Stay stay : stays) {

            //map user
            var hostResponse = mapHost(stay.getUser());
            var Location = maploc();
            //map images
            var images = mapimages(stay.getImages());
            var likedByUsers = mapToStayIds(stay.getLikedByUsers());
//            List<Review> reviews = reviewerService.getReviewsForStay(stay.getId());

           List<ReviewerResponse> reviews = mapToReviews(stay.getReviews());
            var stayResponse =
                    StayResponse.builder()
                            ._id(stay.getId())
                            .name(stay.getName())
                            .price(stay.getPrice())
                            .summary(stay.getSummary())
                            .bathrooms(stay.getBathrooms())
                            .bedrooms(stay.getBedrooms())
                            .imgUrls(images)
                            .type(stay.getType())
                            .loc(Location)
                            .likedByUsers(likedByUsers)
                            .amenities(Collections.emptyList())
                            .reviews(reviews)
                            .capacity(stay.getCapacity())
                            .host(hostResponse).build();

            list.add(stayResponse);

        }
        return list;
    }

    private List<ReviewerResponse> mapToReviews(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewerResponse.builder()
                        .at(review.getReviewAt())
                        .txt(review.getReviewText())
                        .by(ReviewerResponse.By.builder()
                                ._id(review.getReviewer().getId())
                                .imgUrl(review.getReviewer().getImgUrl())
                                .fullname(review.getReviewer().getFullname())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }



    public static List<String> mapToStayIds(List<LikedStay> likedStays) {
        return likedStays.stream()
                .map(LikedStay::getUser)
                .map(User::getId)
                .map(String::valueOf)  // Convert Integer to String
                .collect(Collectors.toList());
    }

    private List<String> mapimages(List<StayImages> images) {

        return images.stream().map(x -> x.getImageUrl()).toList();
    }
}
