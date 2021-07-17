package com.example.nuxel.adsservice.model.entities;

import com.example.nuxel.adsservice.model.entities.dtos.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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
    private String conversationId;
    private String senderId;
}
