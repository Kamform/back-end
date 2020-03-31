package com.design.platform.resourceplatform.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Resource {

    @Id
    @GeneratedValue
    public int id;

    public String title;

    public String summary;

    @CreationTimestamp
    public Timestamp created;

    @UpdateTimestamp
    public Timestamp updated;

    @ManyToOne
    public Category category;

    @ManyToOne
    public User author;

    @ManyToMany
    public List<File> files;

    @ManyToMany(mappedBy = "favorites")
    public List<User> favoriteBy;
}
