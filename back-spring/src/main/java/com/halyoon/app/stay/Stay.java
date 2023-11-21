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
@Entity
@Table(
        name = "stay"
)
public class Stay {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String type;
    private Integer price;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
}
