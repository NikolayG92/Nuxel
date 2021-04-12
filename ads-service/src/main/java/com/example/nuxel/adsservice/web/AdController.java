package com.example.nuxel.adsservice.web;

import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.service.AdService;
import com.example.nuxel.adsservice.service.CategoryService;
import com.example.nuxel.adsservice.service.CloudinaryService;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import com.example.nuxel.adsservice.validators.ad.AdAddValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin(origins = "http://localhost:4200")
public class AdController{

    private final AdService adService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final AdAddValidator adAddValidator;
    private final CloudinaryService cloudinaryService;


    public AdController(AdService adService, CategoryService categoryService, ModelMapper modelMapper, AdAddValidator adAddValidator, CloudinaryService cloudinaryService) {
        this.adService = adService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.adAddValidator = adAddValidator;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping(value = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("ad") @Valid AdAddBindingModel ad,
                                    @RequestPart("files") @Valid MultipartFile[] files)
            throws IOException {
        ad.setFiles(files);
        return ResponseEntity.ok(adService.addAd(ad, files));

    }

}
