package com.halyoon.app.review.stay.media;

import com.halyoon.app.review.stay.Stay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stay_image")
public class StayImages {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    private String imageUrl;
}