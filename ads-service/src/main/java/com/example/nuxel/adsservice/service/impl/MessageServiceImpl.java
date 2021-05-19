package com.example.nuxel.adsservice.service.impl;

import com.example.nuxel.adsservice.model.bindingModels.AdMessageBindingModel;
import com.example.nuxel.adsservice.model.entities.Message;
import com.example.nuxel.adsservice.repository.MessageRepository;
import com.example.nuxel.adsservice.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {
    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;
    private final AdServiceImpl adService;

    @Autowired
    public MessageServiceImpl(ModelMapper modelMapper, MessageRepository messageRepository, AdServiceImpl adService) {
        this.modelMapper = modelMapper;
        this.messageRepository = messageRepository;
        this.adService = adService;
    }

    @Override
    public AdMessageBindingModel sendMassage(AdMessageBindingModel messageBindingModel) {
        Message message = new Message();
        message.setBuyerId(messageBindingModel.getBuyerId());
        message.setSellerId(messageBindingModel.getSellerId());
        message.setDescription(messageBindingModel.getDescription());
        message.setTimeSent(LocalDateTime.now());
        Message currentMassage = messageRepository.saveAndFlush(message);
        this.adService.addMessageToAd(messageBindingModel.getAdId(),currentMassage);
        return this.modelMapper.map(message, AdMessageBindingModel.class);
    }

}
