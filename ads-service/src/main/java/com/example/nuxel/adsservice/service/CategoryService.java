package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.service.serviceModels.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    List<CategoryServiceModel> getAllCategories();

    CategoryServiceModel findByName(String name);

    CategoryServiceModel findCategoryById(String id);
}
