package com.example.nuxel.adsservice.model.bindingModels;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdMessageBindingModel {

    private String description;
    private String buyerId;
    private String sellerId;
    private String adId;

}
