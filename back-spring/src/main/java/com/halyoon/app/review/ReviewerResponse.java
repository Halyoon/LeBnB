package com.halyoon.app.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewerResponse {
    private Date at;
    private By by;
    private String txt;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class By {
        private Integer _id;
        private String fullname;
        private String imgUrl;

    }
}
