package com.example.nuxel.adsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {
    private String city;
    private String region;
    @Column(name = "post_code")
    private int postCode;
    @Column(name ="phone_number")
    private int phoneNumber;

}
