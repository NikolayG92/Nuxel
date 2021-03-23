package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.model.VO.ResponseTemplateVO;

public interface AdService {
    ResponseTemplateVO findAllByUserId(String userId);
}
