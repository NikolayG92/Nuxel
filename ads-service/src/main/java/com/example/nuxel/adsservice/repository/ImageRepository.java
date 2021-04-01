package com.example.nuxel.adsservice.repository;

import com.example.nuxel.adsservice.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,String> {
}
