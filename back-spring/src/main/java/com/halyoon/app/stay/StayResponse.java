package com.halyoon.app.stay;


import com.halyoon.app.review.Review;
import com.halyoon.app.user.UserResponse;
import location.LocationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StayResponse {

    private Integer _id;
    private String name;
    private String type;
    private Double price;
    private String summary;
    private Integer bedrooms;
    private Integer bathrooms;
    private LocationResponse loc;
    private List<String> amenities;
    private int capacity;
    private List<String> imgUrls;
    private List<String> likedByUsers;
    private UserResponse host;
    private List<Review> reviews;

}
