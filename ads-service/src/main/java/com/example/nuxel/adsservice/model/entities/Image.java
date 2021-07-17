package com.example.nuxel.adsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
public class Image extends BaseEntity{
    @Column
    private String url;
    @ManyToOne
    private Ad ad;
}
