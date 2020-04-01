package com.design.platform.resourceplatform.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Admin extends Account {

    @NotBlank
    public String nickname;
}
