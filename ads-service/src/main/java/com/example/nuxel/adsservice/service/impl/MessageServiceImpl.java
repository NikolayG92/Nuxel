package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.bindingModels.AdMessageBindingModel;
import com.example.nuxel.adsservice.model.entities.Ad;
import com.example.nuxel.adsservice.model.entities.Conversation;
import com.example.nuxel.adsservice.model.entities.Message;
import com.example.nuxel.adsservice.repository.AdRepository;
import com.example.nuxel.adsservice.repository.ConversationRepository;
import com.example.nuxel.adsservice.repository.MessageRepository;
import com.example.nuxel.adsservice.service.MessageService;
import com.example.nuxel.adsservice.service.serviceModels.AdServiceModel;
import com.example.nuxel.adsservice.service.serviceModels.MessageServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;
    private final AdServiceImpl adService;
    private final ConversationRepository conversationRepository;
    private final AdRepository adRepository;

    @Autowired
    public MessageServiceImpl(ModelMapper modelMapper, MessageRepository messageRepository, AdServiceImpl adService, ConversationRepository conversationRepository, AdRepository adRepository) {
        this.modelMapper = modelMapper;
        this.messageRepository = messageRepository;
        this.adService = adService;
        this.conversationRepository = conversationRepository;
        this.adRepository = adRepository;
    }

    @Transactional
    @Override
    public AdMessageBindingModel sendMessage(AdMessageBindingModel messageBindingModel) {
        Message message = new Message();
        message.setDescription(messageBindingModel.getDescription());
        message.setTimeSent(LocalDateTime.now());
        message.setSenderId(messageBindingModel.getSenderId());

        if(this.conversationRepository.findByBuyerId(messageBindingModel.getSenderId()) == null){
            if(messageBindingModel.getConversationId() == null){
                Conversation conversation = new Conversation();
                conversation.setAdId(messageBindingModel.getAdId());
                conversation.setBuyerId(messageBindingModel.getSenderId());
                conversation.setMessages(new ArrayList<>());
                conversation.getMessages().add(message);

                Ad ad = this.adRepository.findById(messageBindingModel.getAdId())
                        .orElse(null);
                conversation.setSellerId(ad.getUserId());
                ad.getConversations().add(conversation);

                this.messageRepository.saveAndFlush(message);
                message.setConversationId(this.conversationRepository.saveAndFlush(conversation).getId());
                this.adRepository.saveAndFlush(ad);
            }else {
                Conversation conversation = this.conversationRepository
                        .findById(messageBindingModel.getConversationId()).orElse(null);
                message.setConversationId(conversation.getId());
                conversation.getMessages().add(message);
                this.messageRepository.saveAndFlush(message);
                this.conversationRepository.saveAndFlush(conversation);
            }

        }else if(this.conversationRepository.findByBuyerId(messageBindingModel.getSenderId()) != null
                     && messageBindingModel.getConversationId() != null){
            Conversation conversation = this.conversationRepository
                    .findById(messageBindingModel.getConversationId())
                    .orElse(null);
            message.setConversationId(conversation.getId());
            conversation.getMessages().add(message);
            this.messageRepository.saveAndFlush(message);
            this.conversationRepository.saveAndFlush(conversation);
        }else if(messageBindingModel.getAdId() != null){
            Conversation conversation = new Conversation();
            conversation.setAdId(messageBindingModel.getAdId());
            conversation.setMessages(new ArrayList<>());
            conversation.getMessages().add(message);
            conversation.setBuyerId(messageBindingModel.getSenderId());
            Ad ad = this.adRepository.findById(messageBindingModel.getAdId())
                    .orElse(null);
            conversation.setSellerId(ad.getUserId());
            ad.getConversations().add(conversation);

            this.messageRepository.saveAndFlush(message);
            message.setConversationId(this.conversationRepository.saveAndFlush(conversation).getId());
            this.adRepository.saveAndFlush(ad);
        }

        return this.modelMapper.map(message, AdMessageBindingModel.class);
    }


}
