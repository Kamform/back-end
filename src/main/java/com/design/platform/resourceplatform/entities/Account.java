package com.design.platform.resourceplatform.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
public abstract class Account implements UserDetails {

    @Id
    @GeneratedValue
    @Positive
    public int id;

    @NotBlank
    public String username;

    @NotBlank
    public String password;

    @Column(name = "is_enable")
    public boolean enable;

    @Column(name = "is_lock")
    public boolean lock;

    @CreationTimestamp
    public Timestamp created;

    @UpdateTimestamp
    public Timestamp updated;

    // Methods - UserDetails
    // ===============================================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !lock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
