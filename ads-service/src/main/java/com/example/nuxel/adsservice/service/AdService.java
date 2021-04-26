package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AdService {

    Ad addAd(AdAddBindingModel addBindingModel, MultipartFile[] files) throws IOException;

    List<AdServiceModel> getAllAdsByCategory(String category);

    AdServiceModel getById(String id);
}
