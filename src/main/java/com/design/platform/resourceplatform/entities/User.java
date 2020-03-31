package com.design.platform.resourceplatform.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends Account {

    public String nickname;
    public String email;
    public String phone;

    @ManyToMany
    public List<User> idols;

    @ManyToMany(mappedBy = "idols")
    public List<User> fans;

    @OneToMany
    public List<Resource> resources;

    @ManyToMany
    public List<Resource> favorites;

    @OneToMany
    public List<File> files;
}
