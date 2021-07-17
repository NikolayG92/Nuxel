package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.model.bindingModels.AdMessageBindingModel;
import com.example.nuxel.adsservice.service.serviceModels.MessageServiceModel;

import java.util.List;

public interface MessageService {
    AdMessageBindingModel sendMessage(AdMessageBindingModel message);

}
