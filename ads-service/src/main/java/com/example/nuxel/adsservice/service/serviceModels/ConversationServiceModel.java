package com.example.nuxel.adsservice.service.serviceModels;

import com.example.nuxel.adsservice.model.entities.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ConversationServiceModel extends BaseServiceModel{

    private String adId;
    private String sellerId;
    private String buyerId;
    private List<MessageServiceModel> messages;
}
