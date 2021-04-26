package com.example.nuxel.adsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String imageUrl;

}
