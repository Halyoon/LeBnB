package com.halyoon.app.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {

    private Integer stayId;
    private ReviewerRequest review;
    private String txt;

}
