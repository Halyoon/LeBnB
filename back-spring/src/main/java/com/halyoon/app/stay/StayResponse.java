package com.halyoon.app.stay;


import com.halyoon.app.review.Review;
import com.halyoon.app.stay.media.StayImages;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserResponse;
import jakarta.persistence.*;
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
    private String capacity;
    private List<String> imgUrls;
    private UserResponse host;
    private Review review;

}
