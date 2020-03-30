package com.design.platform.resourceplatform.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    public int id;

    public String name;

    public int sort;

    @OneToMany(mappedBy = "category")
    public List<Resource> resources;
}
