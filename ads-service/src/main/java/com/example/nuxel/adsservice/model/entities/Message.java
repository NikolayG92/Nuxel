package com.example.nuxel.adsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "massages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity {

    @Column
    private String description;
    @Column(name = "time_sent")
    private LocalDateTime timeSent;

//    @ManyToOne
//    private User sender;
//
//    @ManyToOne
//    private User receiver;

}
