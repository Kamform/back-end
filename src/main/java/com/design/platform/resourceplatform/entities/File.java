package com.design.platform.resourceplatform.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class File {

    public enum Type {
        IMAGE, TEXT, CODE, DOCUMENT, COMPRESSED
    }

    @Id
    @GeneratedValue
    public int id;

    public String name;

    public String type;

    public String path;

    @CreationTimestamp
    public Timestamp created;

    @UpdateTimestamp
    public Timestamp updated;

    @ManyToOne
    public User author;

    @ManyToMany(mappedBy = "files")
    public List<Resource> contained;
}
