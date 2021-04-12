package com.example.nuxel.adsservice.model.bindingModels;

import com.example.nuxel.adsservice.model.entities.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AdAddBindingModel {
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String city;
    private String region;
    private int postCode;
    private String phoneNumber;
    private String userId;
    private MultipartFile[] files;
}
