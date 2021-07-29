package com.example.nuxel.adsservice.repository;

import com.example.nuxel.adsservice.model.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findAllByConversationId(String id);
}
