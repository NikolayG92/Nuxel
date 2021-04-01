package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.entities.Address;
import com.example.nuxel.adsservice.repository.AddressRepository;
import com.example.nuxel.adsservice.service.AddressService;
import com.example.nuxel.adsservice.service.serviceModels.AddressServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AddressServiceModel seedAddress(String city, String region, String phoneNumber, int postCode) {
        Address address = new Address();
        address.setCity(city);
        address.setRegion(region);
        address.setPhoneNumber(phoneNumber);
        address.setPostCode(postCode);
        return this.modelMapper.map(addressRepository.saveAndFlush(address),AddressServiceModel.class);
    }
}
