package com.example.nuxel.adsservice.service;

import com.example.nuxel.adsservice.model.bindingModels.AdMessageBindingModel;

public interface MessageService {
    AdMessageBindingModel sendMassage(AdMessageBindingModel massage);
}
