package com.cyecize.skatefixers.areas.users.repositories;


import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {
    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByUsernameOrEmail(String username, String email);
}
