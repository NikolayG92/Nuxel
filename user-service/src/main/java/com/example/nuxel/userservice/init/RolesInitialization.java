package com.example.nuxel.userservice.init;

import com.example.nuxel.userservice.model.entities.Role;
import com.example.nuxel.userservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class RolesInitialization implements CommandLineRunner {
    private RoleRepository roleRepository;

    @Autowired
    public RolesInitialization(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            this.roleRepository.save(new Role("ROLE_ROOT"));
            this.roleRepository.save(new Role("ROLE_ADMIN"));
            this.roleRepository.save(new Role("ROLE_MODERATOR"));
            this.roleRepository.save(new Role("ROLE_USER"));
        }
    }
}
