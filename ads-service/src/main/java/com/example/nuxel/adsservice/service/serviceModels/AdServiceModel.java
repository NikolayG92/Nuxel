package com.example.nuxel.adsservice.service.serviceModels;

import com.example.nuxel.adsservice.model.entities.Address;
import com.example.nuxel.adsservice.model.entities.Category;
import com.example.nuxel.adsservice.model.entities.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AdServiceModel extends BaseServiceModel {
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate date;
    private Address address;
    private Category category;
    private List<Message> messages;
    private List<ImageServiceModel> images;
}
