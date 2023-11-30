package com.halyoon.app.like;

import com.halyoon.app.stay.Stay;
import com.halyoon.app.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "stay_user_like")
public class LikedStay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();
}

