package com.halyoon.app.stay.host;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HostRequest {


    private Integer _id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date createAt;
    private String fullname;
    private String location;
    private String about;
    private String responseTime;
    private String pictureUrl;
    private boolean isSuperhost;
    private String policyNumber;
    private String thumbnailUrl;

}
