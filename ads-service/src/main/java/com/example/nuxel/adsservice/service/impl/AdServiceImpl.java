package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.VO.ResponseTemplateVO;
import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.model.entities.Address;
import com.example.nuxel.adsservice.model.entities.Image;
import com.example.nuxel.adsservice.model.entities.dtos.User;
import com.example.nuxel.adsservice.repository.AdRepository;
import com.example.nuxel.adsservice.service.AdService;
import com.example.nuxel.adsservice.service.AddressService;
import com.example.nuxel.adsservice.service.CloudinaryService;
import com.example.nuxel.adsservice.service.serviceModels.AddressServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final RestTemplate restTemplate;
    private final CloudinaryService cloudinaryService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    public AdServiceImpl(AdRepository adRepository, RestTemplate restTemplate, CloudinaryService cloudinaryService, AddressService addressService, ModelMapper modelMapper) {
        this.adRepository = adRepository;
        this.restTemplate = restTemplate;
        this.cloudinaryService = cloudinaryService;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
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

    @Override
    public void addAd(AdAddBindingModel adAddBindingModel) throws IOException {
        Ad ad = this.modelMapper.map(adAddBindingModel,Ad.class);
        ad.setDate(LocalDate.now());
        AddressServiceModel addressServiceModel = addressService.seedAddress(adAddBindingModel.getCity(), adAddBindingModel.getRegion(),
                adAddBindingModel.getPhoneNumber(), adAddBindingModel.getPostCode());

        ad.setAddress(this.modelMapper.map(addressServiceModel,Address.class));
        List<Image> images = new ArrayList<>();
        for (MultipartFile adImage : adAddBindingModel.getImages()) {
            String imageUrl = adImage.isEmpty() ? "https://res.cloudinary.com/nuxel-application/image/upload/v1616937887/No-image-found_vtfx1x_ggyqam.jpg"
                    : this.cloudinaryService.uploadImageToCurrentFolder(adImage, "products");
            Image image = new Image();
            image.setUrl(imageUrl);
            images.add(image);
        }

        ad.setImages(images);
        this.adRepository.save(ad);
    }
}
