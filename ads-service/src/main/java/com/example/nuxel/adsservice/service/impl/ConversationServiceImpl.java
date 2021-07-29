package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.model.entities.Conversation;
import com.example.nuxel.adsservice.repository.AdRepository;
import com.example.nuxel.adsservice.repository.ConversationRepository;
import com.example.nuxel.adsservice.service.ConversationService;
import com.example.nuxel.adsservice.service.serviceModels.ConversationServiceModel;
import com.example.nuxel.adsservice.service.serviceModels.MessageServiceModel;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final AdRepository adRepository;
    private final ModelMapper modelMapper;

    public ConversationServiceImpl(ConversationRepository conversationRepository, AdRepository adRepository, ModelMapper modelMapper) {
        this.conversationRepository = conversationRepository;
        this.adRepository = adRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ConversationServiceModel> findAllByCurrentUser(String id) {
        List<Conversation> conversations = this.conversationRepository.findAll();
        List<ConversationServiceModel> conversationServiceModels = new ArrayList<>();
        conversations.forEach(conversation -> {
            if(conversation.getBuyerId().equals(id) || conversation.getSellerId().equals(id)){
                conversationServiceModels.add(this.modelMapper
                        .map(conversation, ConversationServiceModel.class));
            }
        });
        return conversationServiceModels.stream()
                .sorted((a, b) ->
                        b.getMessages().get(0).getTimeSent().compareTo(a.getMessages().get(0).getTimeSent()))
                .collect(Collectors.toList());
    }

    @Override
    public ConversationServiceModel createNewConversation(String id, String senderId) {

            Conversation conversation = new Conversation();
            conversation.setBuyerId(senderId);
            conversation.setAdId(id);
            Ad ad = this.adRepository.findById(id).orElse(null);
            conversation.setSellerId(ad.getUserId());
            conversation.setMessages(new ArrayList<>());

            return this.modelMapper.map(this.conversationRepository.saveAndFlush
                    (conversation).getId(), ConversationServiceModel.class);

    }

    @Override
    public ConversationServiceModel findById(String id) {
        Conversation conversation = this.conversationRepository.findById(id)
                .orElse(null);
        conversation.getMessages()
                .sort((a, b) -> b.getTimeSent().compareTo(a.getTimeSent()));
        return this.modelMapper.map(conversation, ConversationServiceModel.class);
    }

}
