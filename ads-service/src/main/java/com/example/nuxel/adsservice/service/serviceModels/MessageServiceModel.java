package com.example.nuxel.adsservice.service.serviceModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MessageServiceModel extends BaseServiceModel{
    private String description;
    private LocalDateTime timeSent;
    private String buyerId;
    private String sellerId;
    private String adId;
}
