package com.halyoon.app.stay;


import com.halyoon.app.amenity.Amenity;
import com.halyoon.app.location.Location;
import com.halyoon.app.review.Review;
import com.halyoon.app.stay.media.StayImages;
import com.halyoon.app.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stay")
public class Stay {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer bedrooms;
    private Integer bathrooms;
    private String name;
    private String type;
    private double price;
    private String summary;
    private Integer capacity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL)
    private List<StayImages> images;


    @ManyToMany
    @JoinTable(name = "stay_amenity", joinColumns = @JoinColumn(name = "stay_id"), inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private List<Amenity> amenities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
