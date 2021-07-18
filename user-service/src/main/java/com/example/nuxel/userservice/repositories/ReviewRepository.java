package com.example.nuxel.userservice.repositories;

import com.example.nuxel.userservice.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findAllBySellerId(String id);

    Review findByBuyerId(String id);
}
