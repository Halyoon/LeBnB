package com.halyoon.app.location;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stay_location")
public class Location {

    @Id
    @GeneratedValue
    private Integer id;
    private String country;
    private String countryCode;
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;
}
