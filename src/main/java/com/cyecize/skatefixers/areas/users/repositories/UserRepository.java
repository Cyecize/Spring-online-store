package com.cyecize.skatefixers.areas.users.repositories;


import com.cyecize.skatefixers.areas.users.entities.User;
import com.cyecize.skatefixers.areas.users.entities.UserRole;
import com.cyecize.skatefixers.areas.users.enums.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, String> {
    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByUsernameOrEmail(String username, String email);

    @Query( "SELECT u FROM User u JOIN u.roles r WHERE r.authority =:rolename" )
    List<User> findBySpecificRoles(@Param("rolename") UserRoleType roleName);
}
