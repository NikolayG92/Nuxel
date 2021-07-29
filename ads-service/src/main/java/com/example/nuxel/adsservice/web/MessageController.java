package com.example.nuxel.adsservice.web;


import com.example.nuxel.adsservice.model.bindingModels.AdMessageBindingModel;
import com.example.nuxel.adsservice.service.ConversationService;
import com.example.nuxel.adsservice.service.MessageService;
import com.example.nuxel.adsservice.service.serviceModels.ConversationServiceModel;
import com.example.nuxel.adsservice.service.serviceModels.MessageServiceModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final MessageService messageService;
    private final ConversationService conversationService;

    public MessageController(MessageService messageService, ConversationService conversationService) {
        this.messageService = messageService;
        this.conversationService = conversationService;
    }

    @PostMapping("/send")
    public ResponseEntity<AdMessageBindingModel> sendMessage(@Valid @RequestBody AdMessageBindingModel message){
        return ResponseEntity.ok(messageService.sendMessage(message));
    }


    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<ConversationServiceModel>> getConversationsByUserId(@PathVariable("id") String id){
        return ResponseEntity.ok(conversationService.findAllByCurrentUser(id));
    }

    @GetMapping("/byConversation/{id}")
    public ResponseEntity<ConversationServiceModel> getConversationById(@PathVariable("id") String id){
        return ResponseEntity.ok(conversationService.findById(id));
    }

    @GetMapping("/createNewConversation/{id}/{senderId}")
    public ResponseEntity<ConversationServiceModel> createNewConversation(@PathVariable("id") String id,
                                                                                     @PathVariable("senderId") String senderId){
        return ResponseEntity.ok(this.conversationService.createNewConversation(id, senderId));
    }


}