package com.example.nuxel.userservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@Getter
@Setter
public class Review extends BaseEntity{
    private String sellerId;
    private String buyerId;
    private Integer rating;
}
