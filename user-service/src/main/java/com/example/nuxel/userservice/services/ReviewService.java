package com.example.nuxel.userservice.services;

import com.example.nuxel.userservice.model.bindingModels.ReviewBindingModel;
import com.example.nuxel.userservice.model.entities.Review;

public interface ReviewService {
    Review addReview(ReviewBindingModel ratingBindingModel);

    Double rating (String id);
}
