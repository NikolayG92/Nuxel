package com.example.nuxel.userservice.repositories;

import com.example.nuxel.userservice.model.entities.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails, String> {
}
