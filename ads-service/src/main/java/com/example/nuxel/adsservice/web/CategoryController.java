package com.example.nuxel.adsservice.web;

import com.example.nuxel.adsservice.service.CategoryService;
import com.example.nuxel.adsservice.service.serviceModels.CategoryServiceModel;
import com.example.nuxel.adsservice.web.viewModels.CategoryViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80"})
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryViewModel> findCategoryById(@PathVariable("id") String id) {

        return ResponseEntity
                .ok()
                .body(this.modelMapper.map(this.categoryService.findCategoryById(id),CategoryViewModel.class));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryViewModel>> getAllCategories(){

        return ResponseEntity.ok(this.categoryService.getAllCategories().stream()
        .map(c -> this.modelMapper.map(c,CategoryViewModel.class )).collect(Collectors.toList()));

    }
}
