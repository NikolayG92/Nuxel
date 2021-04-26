package com.example.nuxel.adsservice.init;

import com.example.nuxel.adsservice.constants.GlobalConstants;
import com.example.nuxel.adsservice.model.entities.Category;
import com.example.nuxel.adsservice.model.entities.dtos.CategoryDto;
import com.example.nuxel.adsservice.repository.CategoryRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Arrays;

@Component
@Order(value = 1)
public class CategoryInitialization implements CommandLineRunner {
    private  CategoryRepository categoryRepository;
    private  Gson gson;

    @Autowired
    public CategoryInitialization(CategoryRepository categoryRepository, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Arrays.stream(this.gson.fromJson(new FileReader(GlobalConstants.CATEGORY_FILE_PATH), CategoryDto[].class))
                    .forEach(
                            e -> {
                                Category category = new Category();
                                category.setName(e.getName());
                                category.setImageUrl(e.getImageUrl());
                                categoryRepository.save(category);
                            }
                    );
        }
    }
}
