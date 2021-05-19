package com.example.nuxel.adsservice.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeSent;
    @Column
    private String buyerId;
    @Column
    private String sellerId;

}
