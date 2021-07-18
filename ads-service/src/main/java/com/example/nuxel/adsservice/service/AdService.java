package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.model.bindingModels.EditAdBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdService {

    Ad addAd(AdAddBindingModel addBindingModel, MultipartFile[] files) throws IOException;

    List<AdServiceModel> getAllAdsByCategory(String category);

    List<AdServiceModel> getAllAdsByWord(String word);

    AdServiceModel getById(String id);

    List<AdServiceModel> getAllAdsByUser(String id);

    AdServiceModel editAd(EditAdBindingModel ad, MultipartFile[] files) throws IOException;

    void removeAd(String adId);
}
