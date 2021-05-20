package com.example.nuxel.adsservice.model.bindingModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class EditAdBindingModel {

    private String adId;
    private String description;
    private BigDecimal price;
    private MultipartFile[] files;
}
