package com.design.platform.resourceplatform.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    public int id;

    @MapsId
    @OneToOne(cascade = CascadeType.REMOVE)
    public Account account;

    public String email;

    public String phone;

    @ManyToMany
    public List<User> followed;

    @ManyToMany(mappedBy = "followed")
    public List<User> followers;

    @OneToMany
    public List<Resource> published;

    @ManyToMany
    public List<Resource> favorites;

    @OneToMany
    public List<File> files;
}
