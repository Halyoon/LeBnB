package com.halyoon.app.stay;

import com.halyoon.app.stay.media.StayImages;
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
    private String capacity;
    private List<String> imgUrls;
}
