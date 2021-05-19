package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
<<<<<<< HEAD
import com.example.nuxel.adsservice.model.bindingModels.EditAdBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.model.entities.Address;
import com.example.nuxel.adsservice.model.entities.Category;
import com.example.nuxel.adsservice.model.entities.Image;
=======
import com.example.nuxel.adsservice.model.entities.*;
>>>>>>> 133b203170696e867d3272f7f44552b564f4c3f2
import com.example.nuxel.adsservice.repository.AdRepository;
import com.example.nuxel.adsservice.service.AdService;
import com.example.nuxel.adsservice.service.AddressService;
import com.example.nuxel.adsservice.service.CategoryService;
import com.example.nuxel.adsservice.service.CloudinaryService;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import com.example.nuxel.adsservice.service.serviceModels.AddressServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import javax.persistence.EntityNotFoundException;
import javax.persistence.Table;
=======
>>>>>>> 133b203170696e867d3272f7f44552b564f4c3f2
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        this.adRepository.save(ad);
        return ad;
    }

    @Override
    public List<AdServiceModel> getAllAdsByCategory(String id) {
             return this.adRepository.findAllByCategoryId(id).stream()
                .map(c -> this.modelMapper.map(c, AdServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdServiceModel getById(String id) {
        Ad ad = this.adRepository.findById(id).orElse(null);
        return this.modelMapper.map(ad, AdServiceModel.class);
    }

<<<<<<< HEAD
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
=======
    public void addMessageToAd(String adId, Message currentMassageId) {
        Ad ad = this.adRepository.findById(adId).orElse(null);
        ad.getMessages().add(currentMassageId);
        this.adRepository.save(ad);
>>>>>>> 133b203170696e867d3272f7f44552b564f4c3f2
    }
}
