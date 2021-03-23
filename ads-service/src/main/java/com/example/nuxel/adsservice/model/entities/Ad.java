package com.example.nuxel.adsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name ="ads")
@Getter
@Setter
@NoArgsConstructor
public class Ad extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private LocalDate date;

    private String userId;
    @ManyToOne
    private Category category;
    @OneToMany
    private List<Massage> massages;

}
