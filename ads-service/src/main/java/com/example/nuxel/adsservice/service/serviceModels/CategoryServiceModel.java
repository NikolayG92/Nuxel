package com.example.nuxel.adsservice.service.serviceModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryServiceModel extends BaseServiceModel{
    private String name;
    private String imageUrl;
}
