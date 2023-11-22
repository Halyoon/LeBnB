package com.halyoon.app.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class UserResponse {

    private Integer _id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date createAt;

    private String fullname;
    private String location;
    private String about;
    private String responseTime;
    private String pictureUrl;
    private boolean isSuperhost;
//    private String policyNumber;
}
