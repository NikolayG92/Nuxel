package com.example.nuxel.userservice.services.impl;

import com.example.nuxel.userservice.model.bindingModels.ReviewBindingModel;
import com.example.nuxel.userservice.model.entities.Review;
import com.example.nuxel.userservice.repositories.ReviewRepository;
import com.example.nuxel.userservice.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Review addReview(ReviewBindingModel reviewBindingModel) {
        Review review = null;
        if (this.reviewRepository.findAll().size() == 0 &&
                !reviewBindingModel.getBuyerId().equals(reviewBindingModel.getSellerId())) {
            review = this.reviewRepository.save(this.modelMapper.map(reviewBindingModel, Review.class));
        } else {
            for (Review r : this.reviewRepository.findAllBySellerId(reviewBindingModel.getSellerId())) {
                if (!r.getBuyerId().equals(reviewBindingModel.getBuyerId()) &&
                        !r.getSellerId().equals(reviewBindingModel.getBuyerId()) &&
                         this.reviewRepository.findByBuyerId(reviewBindingModel.getBuyerId()) == null) {
                    review = this.reviewRepository.save(this.modelMapper.map(reviewBindingModel, Review.class));
                }
            }
        }
        return review;
    }

    @Override
    public Double rating (String id) {
        List<Integer> starsList = new ArrayList<>();
        reviewRepository.findAllBySellerId(id).forEach(r -> {
            starsList.add(r.getRating());
        });
        return ratingCounting(starsList);
    }

    private double ratingCounting(List<Integer> stars) {
        int oneStar = 0;int twoStar = 0;int threeStar = 0;int fourStar = 0;int fiveStar = 0;
        for (Integer star : stars) {
            if (star == 1) {
                oneStar++;
            } else if (star == 2) {
                twoStar++;
            } else if (star == 3) {
                threeStar++;
            } else if (star == 4) {
                fourStar++;
            } else if (star == 5) {
                fiveStar++;
            }
        }
        return (5.0 * fiveStar + 4.0 * fourStar + 3.0 * threeStar + 2.0 * twoStar + oneStar) / stars.size();
    }

}
