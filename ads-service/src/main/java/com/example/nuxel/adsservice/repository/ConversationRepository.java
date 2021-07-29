package com.example.nuxel.adsservice.repository;

import com.example.nuxel.adsservice.model.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String> {

    List<Conversation> findAllByBuyerIdAndSellerId(String buyerId, String sellerId);

    List<Conversation> findAllByBuyerId(String id);

    List<Conversation> findAllByAdId(String id);

}
