package com.halyoon.app.user;

import com.halyoon.app.like.LikedStay;
import com.halyoon.app.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

//@Data is for the getters and setters
//builder design patterns the builder is helpful
//when using builder we need to include the AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
//@Id before the integer id means it well be the primary key of the table
// and @GeneratedValue means the type of the primary key (auto increment or ...)
// df value well give the best solution for the table


    private String fullname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String secret;
    private String imgUrl;
    private boolean isSuperhost;
    private Long userMsg;
    private Long hostMsg;
    private String location;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();
    private String about;
    private String responseTime;

    @OneToMany(mappedBy = "user")
    private List<LikedStay> likedStays;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
