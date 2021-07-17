package com.example.nuxel.adsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="ads")
@Getter
@Setter
@NoArgsConstructor
public class Ad extends BaseEntity{
    @Column
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private LocalDate date;

    private String userId;
    @ManyToOne
    private Address address;
    @ManyToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Conversation> conversations = new ArrayList<>();
}
