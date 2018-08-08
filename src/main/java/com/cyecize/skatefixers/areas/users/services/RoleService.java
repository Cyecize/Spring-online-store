package com.cyecize.skatefixers.areas.users.services;




import com.cyecize.skatefixers.areas.users.entities.UserRole;

import java.util.List;

public interface RoleService {
    List<UserRole> findAllRoles();

    UserRole findRoleByName(String roleName);

}
