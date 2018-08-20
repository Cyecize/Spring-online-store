package com.cyecize.skatefixers.areas.orders.repositories;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
