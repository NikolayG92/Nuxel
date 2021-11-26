package com.example.nuxel.adsservice.init;

import com.example.nuxel.adsservice.constants.GlobalConstants;
import com.example.nuxel.adsservice.model.entities.Category;
import com.example.nuxel.adsservice.model.entities.dtos.CategoryDto;
import com.example.nuxel.adsservice.repository.CategoryRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
@Order(value = 1)
public class CategoryInitialization implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private Gson gson;

    @Autowired
    public CategoryInitialization(CategoryRepository categoryRepository, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            try {
                Arrays.stream(this.gson.fromJson(new FileReader(GlobalConstants.CATEGORY_FILE_PATH), CategoryDto[].class))
                        .forEach(
                                e -> {
                                    Category category = new Category();
                                    category.setName(e.getName());
                                    category.setImageUrl(e.getImageUrl());
                                    categoryRepository.save(category);
                                }
                        );

            } catch (Exception e) {
                String parsedJSONString = "";
                ClassPathResource cpr = new ClassPathResource(GlobalConstants.CATEGORY_FILE_PATH_WITH_BUILD);
                byte[] bdata = null;
                try {
                    bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                parsedJSONString = new String(bdata, StandardCharsets.UTF_8);
                Arrays.stream(this.gson.fromJson(parsedJSONString, CategoryDto[].class))
                        .forEach(
                                c -> {
                                    Category category = new Category();
                                    category.setName(c.getName());
                                    category.setImageUrl(c.getImageUrl());
                                    categoryRepository.save(category);
                                });
            }
        }
    }
}
