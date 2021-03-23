package com.example.nuxel.adsservice.web;

import com.example.nuxel.adsservice.model.VO.ResponseTemplateVO;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.service.AdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> getAllAdsByUserId(@PathVariable String id){
        int a = 5;
        return ResponseEntity.ok(adService.findAllByUserId(id));
    }
}
