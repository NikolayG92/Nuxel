package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.entities.Category;
import com.example.nuxel.adsservice.repository.CategoryRepository;
import com.example.nuxel.adsservice.service.CategoryService;
import com.example.nuxel.adsservice.service.serviceModels.CategoryServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryServiceModel> getAllCategories() {
        return this.categoryRepository.findAll().stream()
                .map(c-> this.modelMapper.map(c,CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryServiceModel findByName(String name) {
        return this.modelMapper.map(
                this.categoryRepository.findByName(name), CategoryServiceModel.class);
    }

    @Override
    public CategoryServiceModel findCategoryById(String id) {
        Category category = this.categoryRepository.findById(id).orElse(null);
        return this.modelMapper.map(category,CategoryServiceModel.class);
    }
}
