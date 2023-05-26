package com.restapi.restapi.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.restapi.models.booking.Booking;
import com.restapi.restapi.models.vanue.Rating;
import com.restapi.restapi.models.vanue.Venue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Venue> venues;
    @OneToMany(mappedBy = "booker")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "rater")
    @JsonIgnore
    private List<Rating> ratings;
    //@JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo info;
    @OneToOne(cascade = CascadeType.ALL)
    private UserMedia media;
    @OneToOne(cascade = CascadeType.ALL)
    private UserAddress address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Date created;
    private Date updated;
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // Returnerar en lista av roller
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
