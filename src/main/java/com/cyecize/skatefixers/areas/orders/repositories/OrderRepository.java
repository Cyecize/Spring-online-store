package com.cyecize.skatefixers.areas.orders.repositories;

import com.cyecize.skatefixers.areas.orders.entities.Order;
import com.cyecize.skatefixers.areas.orders.enums.OrderStatus;
import com.cyecize.skatefixers.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM  Order o WHERE o.user = :user ORDER BY o.orderDate DESC ")
    List<Order> findByUser(@Param("user") User user);


    @Query("SELECT o FROM Order  o WHERE  o.orderStatus = :status ORDER BY o.orderDate DESC")
    List<Order> findByOrderStatus(@Param("status") OrderStatus status);
}
