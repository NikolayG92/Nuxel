package com.example.nuxel.adsservice.web;

import com.example.nuxel.adsservice.model.bindingModels.AdAddBindingModel;
import com.example.nuxel.adsservice.model.bindingModels.EditAdBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.service.AdService;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin(origins = "http://localhost:4200")
public class AdController{

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
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

    @PostMapping(value = "/editAdd",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdServiceModel> editAdd(@RequestPart("ad") @Valid EditAdBindingModel ad,
                                    @RequestPart("files") @Valid MultipartFile[] files)
            throws IOException {
        ad.setFiles(files);
        return ResponseEntity.ok(adService.editAd(ad, files));

    }

    @GetMapping("/allByCategory/{id}")
    public ResponseEntity<List<AdServiceModel>> getAllAds(@PathVariable("id") String id){
        List<AdServiceModel> allAdsByCategory = this.adService.getAllAdsByCategory(id);
        return ResponseEntity
                .ok()
                .body(allAdsByCategory);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<AdServiceModel> getById(@PathVariable("id") String id){

        return ResponseEntity.ok(this.adService.getById(id));
    }

    @GetMapping("/getAdsByUser/{id}")
    public ResponseEntity<List<AdServiceModel>> getAdsByUser(@PathVariable("id") String id){
        List<AdServiceModel> adsByUser = this.adService.getAllAdsByUser(id);

        return ResponseEntity
                .ok()
                .body(adsByUser);
    }

}
