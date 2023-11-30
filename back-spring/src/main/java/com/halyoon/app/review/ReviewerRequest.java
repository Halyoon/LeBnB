package com.halyoon.app.review;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class ReviewerRequest {

    private long at;
    private Integer stayId;
    private By by;

    private String txt;

    @Data
    @Builder
    public static class By {
        private Integer _id;
        private String fullname;
        private String imgUrl;
    }
}
