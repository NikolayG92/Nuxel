package com.example.nuxel.adsservice.repository;

import com.example.nuxel.adsservice.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

}
