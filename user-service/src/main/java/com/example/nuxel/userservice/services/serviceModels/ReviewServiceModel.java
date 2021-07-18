package com.example.nuxel.userservice.services.serviceModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewServiceModel {
    private String id;
    private String sellerId;
    private String buyerId;
    private Integer rating;
}
