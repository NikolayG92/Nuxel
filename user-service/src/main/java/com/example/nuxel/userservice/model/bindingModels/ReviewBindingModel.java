package com.example.nuxel.userservice.model.bindingModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewBindingModel {
    private String id;
    private String sellerId;
    private String buyerId;
    private Integer rating;
}
