package com.cyecize.skatefixers.areas.users.services;



import com.cyecize.skatefixers.areas.users.entities.UserRole;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import com.cyecize.skatefixers.areas.users.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserRole> findAllRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public UserRole findRoleByName(String roleName) {
        return roleRepository.findUserRoleByAuthority(UserRoleType.valueOf(roleName.toUpperCase()));
    }
}
