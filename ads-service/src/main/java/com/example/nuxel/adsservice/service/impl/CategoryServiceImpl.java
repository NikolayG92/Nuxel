package com.example.nuxel.adsservice.service.impl;

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
}
