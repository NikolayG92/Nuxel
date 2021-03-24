package com.example.nuxel.userservice.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class AdDTO {

    private String id;
    private String name;
    private String description;
    private double price;
    private LocalDate date;

}
