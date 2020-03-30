package com.design.platform.resourceplatform.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
public class Account implements UserDetails {

    @Id
    @GeneratedValue
    public int id;

    public String username;
    public String password;

    @Column(name = "is_admin")
    public boolean admin;

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
        if (admin)
            return List.of(new SimpleGrantedAuthority("ADMIN"));
        else
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
