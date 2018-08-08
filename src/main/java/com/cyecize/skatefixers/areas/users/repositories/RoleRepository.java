package com.cyecize.skatefixers.areas.users.repositories;



import com.cyecize.skatefixers.areas.users.entities.UserRole;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole, String> {
    UserRole findUserRoleByAuthority(UserRoleType auth);
}
