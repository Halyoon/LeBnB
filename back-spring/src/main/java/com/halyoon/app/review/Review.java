package com.halyoon.app.review;
import com.halyoon.app.user.User;
import com.halyoon.app.stay.Stay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stay_review")
public class Review {

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    private String reviewText;
}
