package com.halyoon.app.review.stay;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StayRequest {

    private String name;
    private String type;
    private Double price;
    private String summary;
    private Integer bedrooms;
    private Integer bathrooms;
    private int capacity;
    private List<String> imgUrls;
}
