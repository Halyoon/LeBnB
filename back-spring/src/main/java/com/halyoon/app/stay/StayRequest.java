package com.halyoon.app.stay;

import com.halyoon.app.stay.host.HostRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StayRequest {

    private String name;
    private String type;
    private double price;
    private String summary;
    private Integer bedrooms;
    private Integer bathrooms;
    private HostRequest host;
    private int capacity;
    private List<String> LikedStay;
    private String roomType;
    private List<String> imgUrls;
    private List<String> amenities;
}
