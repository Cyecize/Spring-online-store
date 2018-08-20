package com.cyecize.skatefixers.areas.users.repositories;

import com.cyecize.skatefixers.areas.users.entities.Address;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user = :user AND a.active = true ")
    List<Address> findByUser(@Param("user") User user);
}
