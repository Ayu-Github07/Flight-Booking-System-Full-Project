package com.example.usermanagementservice.services;

import com.example.usermanagementservice.models.Role;
import com.example.usermanagementservice.repository.RoleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role setRoleDetails(Role role) {
        return roleRepo.save(role);
    }
}
