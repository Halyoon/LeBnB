package com.halyoon.app.stay;


import com.halyoon.app.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private User host;
}
