package com.example.nuxel.userservice.services;

import com.example.nuxel.userservice.services.serviceModels.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);
}
