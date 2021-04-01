package com.example.nuxel.adsservice.service.serviceModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressServiceModel extends BaseServiceModel{
    private String city;
    private String region;
    private int postCode;
    private String phoneNumber;
}
