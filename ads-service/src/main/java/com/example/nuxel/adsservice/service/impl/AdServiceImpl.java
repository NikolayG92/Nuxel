package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.VO.ResponseTemplateVO;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.model.entities.dtos.User;
import com.example.nuxel.adsservice.repository.AdRepository;
import com.example.nuxel.adsservice.service.AdService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final RestTemplate restTemplate;

    public AdServiceImpl(AdRepository adRepository, RestTemplate restTemplate) {
        this.adRepository = adRepository;
        this.restTemplate = restTemplate;
    }


    @Override
    public ResponseTemplateVO findAllByUserId(String userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        List<Ad> ads = adRepository.findAllByUserId(userId);
        User user = restTemplate.getForObject("http://USER-SERVICE/api/users/" + userId,User.class);
        vo.setAds(ads);
        vo.setUser(user);
        return vo;
    }
}
