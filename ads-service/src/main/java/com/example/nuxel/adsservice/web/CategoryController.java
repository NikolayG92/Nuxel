package com.example.nuxel.adsservice.web;

import com.example.nuxel.adsservice.service.CategoryService;
import com.example.nuxel.adsservice.service.serviceModels.CategoryServiceModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<CategoryServiceModel>> getAllCategories(){

        return ResponseEntity.ok(this.categoryService.getAllCategories());

    }
}
