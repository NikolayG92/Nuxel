package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdService {

    Ad addAd(AdAddBindingModel addBindingModel, MultipartFile[] files) throws IOException;
}
