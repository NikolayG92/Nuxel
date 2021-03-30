package com.example.nuxel.adsservice.service;


import com.example.nuxel.adsservice.service.serviceModels.AddressServiceModel;

public interface AddressService {
    AddressServiceModel seedAddress(String city, String region, String phoneNumber, int postCode);
}
