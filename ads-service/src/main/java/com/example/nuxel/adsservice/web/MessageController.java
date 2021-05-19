package com.example.nuxel.adsservice.web;

import com.example.nuxel.adsservice.model.bindingModels.AdMessageBindingModel;
import com.example.nuxel.adsservice.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<AdMessageBindingModel> sendMessage(@Valid @RequestBody AdMessageBindingModel message){
        return ResponseEntity.ok(messageService.sendMassage(message));
    }

}