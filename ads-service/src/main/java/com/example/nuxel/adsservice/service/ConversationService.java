package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.service.serviceModels.ConversationServiceModel;
import javassist.NotFoundException;

import java.util.List;

public interface ConversationService {

    List<ConversationServiceModel> findAllByCurrentUser(String id);

    ConversationServiceModel createNewConversation(String id, String senderId);

    ConversationServiceModel findById(String id);
}
