package com.example.nuxel.adsservice.repository;

import com.example.nuxel.adsservice.model.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad,String> {
    List<Ad> findAllByUserId(String id);

    Ad findByName(String name);
}
