package com.example.nuxel.adsservice.repository;

import com.example.nuxel.adsservice.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {

}
