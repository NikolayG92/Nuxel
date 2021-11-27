package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.model.bindingModels.EditAdBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.model.entities.Address;
import com.example.nuxel.adsservice.model.entities.Category;
import com.example.nuxel.adsservice.model.entities.Image;
import com.example.nuxel.adsservice.repository.AdRepository;
import com.example.nuxel.adsservice.service.AdService;
import com.example.nuxel.adsservice.service.AddressService;
import com.example.nuxel.adsservice.service.CategoryService;
import com.example.nuxel.adsservice.service.CloudinaryService;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import com.example.nuxel.adsservice.service.serviceModels.AddressServiceModel;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final RestTemplate restTemplate;
    private final CloudinaryService cloudinaryService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public AdServiceImpl(AdRepository adRepository, RestTemplate restTemplate, CloudinaryService cloudinaryService, AddressService addressService, ModelMapper modelMapper, CategoryService categoryService) {
        this.adRepository = adRepository;
        this.restTemplate = restTemplate;
        this.cloudinaryService = cloudinaryService;
        this.addressService = addressService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public Ad addAd(AdAddBindingModel adAddBindingModel, MultipartFile[] files) throws IOException {
        Ad ad = this.modelMapper.map(adAddBindingModel,Ad.class);
        ad.setDate(LocalDate.now());
        AddressServiceModel addressServiceModel = addressService.seedAddress(adAddBindingModel.getCity(), adAddBindingModel.getRegion(),
                adAddBindingModel.getPhoneNumber(), adAddBindingModel.getPostCode());

        ad.setAddress(this.modelMapper.map(addressServiceModel,Address.class));
        List<Image> images = new ArrayList<>();
        for (MultipartFile adImage : files) {
            String imageUrl = adImage.isEmpty() ? "https://res.cloudinary.com/nuxel-application/image/upload/v1616937887/No-image-found_vtfx1x_ggyqam.jpg"
                    : this.cloudinaryService.uploadImageToCurrentFolder(adImage, "products");
            Image image = new Image();
            image.setUrl(imageUrl);
            images.add(image);
        }
        Category category = this.modelMapper.map(this.categoryService
                .findByName(adAddBindingModel.getCategory()), Category.class);
        ad.setImages(images);
        ad.setCategory(category);
        try{
            this.adRepository.save(ad);
            log.info(String.format("Ad %s has being added", adAddBindingModel.getName()));
        }catch (Exception e){
            log.error("Ad %s could not be added", adAddBindingModel.getName());
        }

        return ad;
    }

    @Override
    public List<AdServiceModel> getAllAdsByCategory(String id) {
             return this.adRepository.findAllByCategoryId(id).stream()
                .map(c -> this.modelMapper.map(c, AdServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AdServiceModel> getAllAdsByWord(String word) {

        return this.adRepository.findAll().stream()
                .filter(a -> a.getName().toLowerCase().contains(word.toLowerCase()))
                .map(a -> this.modelMapper.map(a, AdServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdServiceModel getById(String id) {
        Ad ad = this.adRepository.findById(id).orElse(null);
        return this.modelMapper.map(ad, AdServiceModel.class);
    }

    @Transactional
    @Override
    public List<AdServiceModel> getAllAdsByUser(String id) {
        return this.adRepository.findAllByUserId(id).stream()
                .map(c -> this.modelMapper.map(c, AdServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdServiceModel editAd(EditAdBindingModel ad, MultipartFile[] files) throws IOException {
        Ad currentAd = this.adRepository.findById(ad.getAdId()).orElseThrow(() ->
                new EntityNotFoundException("Ad with that id is not found!"));

        currentAd.setDescription(ad.getDescription());
        List<Image> images = new ArrayList<>();
        for (MultipartFile adImage : files) {
            String imageUrl = adImage.isEmpty() ? "https://res.cloudinary.com/nuxel-application/image/upload/v1616937887/No-image-found_vtfx1x_ggyqam.jpg"
                    : this.cloudinaryService.uploadImageToCurrentFolder(adImage, "products");
            Image image = new Image();
            image.setUrl(imageUrl);
            images.add(image);
        }
        currentAd.setImages(images);

        currentAd.setPrice(ad.getPrice());
        this.adRepository.saveAndFlush(currentAd);

        return this.modelMapper.map(ad, AdServiceModel.class);
    }

    @Override
    public void removeAd(String id) {
        Ad ad = Objects.requireNonNull(this.adRepository.findById(id).orElse(null));
        this.adRepository.delete(ad);
    }

}

