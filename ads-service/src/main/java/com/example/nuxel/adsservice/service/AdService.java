package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.model.VO.ResponseTemplateVO;
import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;

import java.io.IOException;

public interface AdService {
    ResponseTemplateVO findAllByUserId(String userId);

    void addAd(AdAddBindingModel addBindingModel) throws IOException;
}
